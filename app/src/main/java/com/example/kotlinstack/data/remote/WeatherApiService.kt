package com.example.kotlinstack.data.remote

import com.example.kotlinstack.data.models.WeatherModel
import retrofit2.http.GET
import retrofit2.http.Query

// Some retrofit end point
interface WeatherApiService {
    @GET("data/2.5/weather?lat=24.8607&lon=67.0011&units=metric")
    suspend fun getData(@Query("appid") appId: String): WeatherModel
}
