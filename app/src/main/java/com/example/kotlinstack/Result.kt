package com.example.kotlinstack

sealed class Result<T> {
    data class Success<T>(val data : T) : Result<T>()
    data class Failure<T>(val code : Int, val message : String ) : Result<T>()
}