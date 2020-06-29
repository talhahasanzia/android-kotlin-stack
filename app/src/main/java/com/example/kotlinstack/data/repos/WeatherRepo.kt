package com.example.kotlinstack.data.repos

import com.example.kotlinstack.BuildConfig
import com.example.kotlinstack.data.models.WeatherModel
import com.example.kotlinstack.data.remote.Result
import com.example.kotlinstack.data.remote.WeatherApiService
import com.squareup.moshi.Moshi
import javax.inject.Inject

class DefaultWeatherRepo @Inject constructor(moshi: Moshi, private val weatherApiService: WeatherApiService) :
    DefaultRepo(moshi),
    WeatherRepo {

    override suspend fun getData(): Result<WeatherModel> {
        val apiKey = BuildConfig.OPEN_WEATHER_API
        return safeApiCall { weatherApiService.getData(apiKey) }

    }
}

interface WeatherRepo {
    suspend fun getData(): Result<WeatherModel>
}


