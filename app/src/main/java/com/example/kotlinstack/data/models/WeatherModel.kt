package com.example.kotlinstack.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable


data class WeatherModel(
    @Json(name = "main") val main: Main
) : Serializable


data class Main(
    @Json(name = "temp") val temp: Double,
    @Json(name = "feels_like") val feels: Double,
    @Json(name = "temp_min") val min: Double,
    @Json(name = "temp_max") val max: Double,
    @Json(name = "pressure") val pressure: Double,
    @Json(name = "humidity") val humidity: Double
) : Serializable