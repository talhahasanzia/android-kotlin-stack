package com.example.kotlinstack

import androidx.hilt.lifecycle.ViewModelInject
import kotlinx.coroutines.delay
import javax.inject.Inject

class DefaultMainRepo{

    suspend fun getData(): String {
        // Some blocking tasks, retrofit call
        delay(3000)
        return "Hello world"
    }
}

interface MainRepo {
    suspend fun getData(): String
}


