package com.example.kotlinstack.data.remote

sealed class ResponseModel<T>

data class Success<T>(val data: T) : ResponseModel<T>()
data class Failure<T>(val code: Int, val message: String) : ResponseModel<T>()
