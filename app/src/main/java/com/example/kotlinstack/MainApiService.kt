package com.example.kotlinstack

import retrofit2.http.GET
// Some retrofit end point
interface MainApiService {
    @GET("api")
    suspend fun getData(): Result<MainModel>
}
