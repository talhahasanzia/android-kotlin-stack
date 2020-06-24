package com.example.kotlinstack

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.delay


class DefaultMainViewModel @ViewModelInject constructor() :
    ViewModel(), MainViewModel {

    override fun getData(): LiveData<String> {
        return liveData {
            // live data scope automatically allows suspend call
            repeat(10) {
                delay(1000)
                emit("Hello $it times")
            }
        }
    }
}

interface MainViewModel {
    fun getData(): LiveData<String>
}