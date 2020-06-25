package com.example.kotlinstack

import kotlinx.coroutines.delay
import retrofit2.Retrofit

class DefaultMainRepo(private val apiHelper: ApiHelper) : MainRepo {

    override suspend fun getData(): String {
        // Some blocking tasks, retrofit call using API helper
        val result = apiHelper.getData()
//        when(result){
//            is Result.Success -> result.data // do processing with data, pass/return this data to viewmodel
//            is Result.Failure -> result.message // do something with error message
//        }
        delay(3000)
        return "Hello world"
    }
}

interface MainRepo {
    suspend fun getData(): String
}


