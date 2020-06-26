package com.example.kotlinstack.home.vm

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.kotlinstack.data.remote.Failure
import com.example.kotlinstack.data.remote.Success
import com.example.kotlinstack.data.repos.WeatherRepo
import com.example.kotlinstack.utils.ErrorMessageParser
import kotlinx.coroutines.launch


class DefaultHomeViewModel @ViewModelInject constructor(
    private val weatherRepo: WeatherRepo,
    private val errorMessageParser: ErrorMessageParser,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel(), HomeViewModel {

    private val _weatherUpdate = MutableLiveData<String>()
    override val weatherUpdate: LiveData<String>
        get() = _weatherUpdate

    private val _weatherError = MutableLiveData<String>()
    override val weatherError: LiveData<String>
        get() = _weatherError

    init {
        viewModelScope.launch {
            when (val result = weatherRepo.getData()) {
                is Success -> _weatherUpdate.value =
                    "Current temperature is ${result.data.main.temp}"
                is Failure -> _weatherError.value = errorMessageParser.getErrorMessage(result.code)
            }
        }
    }

}

interface HomeViewModel {
    val weatherUpdate: LiveData<String>
    val weatherError: LiveData<String>
}