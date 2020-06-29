package com.example.kotlinstack.data.remote

import com.example.kotlinstack.BuildConfig
import com.example.kotlinstack.data.models.WeatherModel
import com.squareup.moshi.Moshi
import retrofit2.HttpException
import java.io.IOException

class DefaultApiHelper(
    private val moshi: Moshi,
    private val mainApiService: WeatherApiService
) : ApiHelper {

    override suspend fun getData(): Result<WeatherModel> {
        val apiKey = BuildConfig.OPEN_WEATHER_API
        return safeApiCall { mainApiService.getData(apiKey) }
    }

    // Can be used with all calls
    private suspend fun <T> safeApiCall(apiCall: suspend () -> T): Result<T> {
        return try {
            Result.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> Result.NetworkError
                is HttpException -> {
                    val code = throwable.code()
                    val errorResponse = convertErrorBody(throwable)
                    Result.Error(code, errorResponse)
                }
                else -> {
                    Result.Error(null, null)
                }
            }
        }

    }

    private fun convertErrorBody(throwable: HttpException): ErrorResponse? {
        return try {
            throwable.response()?.errorBody()?.source()?.let {
                moshi.adapter(ErrorResponse::class.java).fromJson(it)
            }
        } catch (exception: Exception) {
            null
        }
    }

}

interface ApiHelper {
    suspend fun getData(): Result<WeatherModel>
}