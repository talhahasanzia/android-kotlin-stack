package com.example.kotlinstack

import com.squareup.moshi.Json

data class MainModel(
    @Json(name = "list_data")
    val data: List<String>
)