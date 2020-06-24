package com.example.kotlinstack

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.delay


class DefaultMainViewModel @ViewModelInject constructor(
    private val mainRepo: MainRepo,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel(), MainViewModel {


    override fun getData(): LiveData<String> {
        return liveData {
            // live data scope automatically allows suspend call
            repeat(10) {
                emit("${mainRepo.getData()}  $it times!")
            }
        }
    }
}

interface MainViewModel {
    fun getData(): LiveData<String>
}