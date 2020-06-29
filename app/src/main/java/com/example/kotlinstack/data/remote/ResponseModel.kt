package com.example.kotlinstack.data.remote


sealed class Result<out T> {
    data class Success<out T>(val value: T): Result<T>()
    data class Error(val code: Int? = null, val error: ErrorResponse? = null): Result<Nothing>()
    object NetworkError: Result<Nothing>()
}

// TODO: Map to specific error response
data class ErrorResponse(val code : Int)
