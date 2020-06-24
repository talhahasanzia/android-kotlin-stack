package com.example.kotlinstack

class ApiHelper(private val mainApiService: MainApiService){

    suspend fun getData() : Result<MainModel>{
        return mainApiService.getData()
    }

}