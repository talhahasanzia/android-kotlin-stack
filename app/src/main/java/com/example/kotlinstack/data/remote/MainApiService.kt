package com.example.kotlinstack.data.remote

import com.example.kotlinstack.data.models.WeatherModel
import retrofit2.http.GET
// Some retrofit end point
interface MainApiService {
    @GET("data/2.5/weather?lat=24.8607&lon=67.0011&appid= <API_KEY_HERE> &units=metric")
    suspend fun getData(): WeatherModel
}
