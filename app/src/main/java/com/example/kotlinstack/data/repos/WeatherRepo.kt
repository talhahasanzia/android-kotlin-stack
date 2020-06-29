package com.example.kotlinstack.data.repos

import com.example.kotlinstack.data.models.WeatherModel
import com.example.kotlinstack.data.remote.ApiHelper
import com.example.kotlinstack.data.remote.Result

class DefaultWeatherRepo(private val apiHelper: ApiHelper) :
    WeatherRepo {

    override suspend fun getData(): Result<WeatherModel> {
        // Some blocking tasks, retrofit call using API helper
        // here only api is used, we can check of data is cached in database return that or both
        return apiHelper.getData()

    }
}

interface WeatherRepo {
    suspend fun getData(): Result<WeatherModel>
}


