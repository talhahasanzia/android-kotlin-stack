package com.example.kotlinstack.data.repos

import com.example.kotlinstack.data.remote.ErrorResponse
import com.squareup.moshi.Moshi
import com.example.kotlinstack.data.remote.Result
import retrofit2.HttpException
import java.io.IOException

abstract class DefaultRepo(private val moshi: Moshi) {

    protected suspend fun <T> safeApiCall(apiCall: suspend () -> T): Result<T> {
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