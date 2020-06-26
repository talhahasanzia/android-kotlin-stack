package com.example.kotlinstack.data.remote

import com.example.kotlinstack.data.models.WeatherModel
import java.lang.Exception

class DefaultApiHelper(private val mainApiService: MainApiService) : ApiHelper {

    override suspend fun getData(): ResponseModel<WeatherModel> {
        val responseModel : ResponseModel<WeatherModel>
        responseModel = try {
            Success(mainApiService.getData())
        }catch (ex : Exception){
            Failure(0, ex.message!!)
        }
        return responseModel
    }

}

interface ApiHelper {
    suspend fun getData(): ResponseModel<WeatherModel>
}