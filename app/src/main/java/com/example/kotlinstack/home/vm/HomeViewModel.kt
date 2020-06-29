package com.example.kotlinstack.home.vm

import android.view.View
import android.widget.Button
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.kotlinstack.data.models.WeatherModel
import com.example.kotlinstack.data.remote.Result
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

    private val _isButtonVisible = MutableLiveData<Int>()
    override val isButtonVisible: LiveData<Int>
        get() = _isButtonVisible


    override fun onPushed() {

        viewModelScope.launch {
            when (val result = weatherRepo.getData()) {
                is Result.Success -> showTemperature(result)
                is Result.Error -> showError(result)
            }
        }
    }

    private fun showTemperature(result: Result.Success<WeatherModel>) {
        _isButtonVisible.value = View.INVISIBLE
        _weatherUpdate.value = "Current temperature is ${result.value.main.temp}"
    }

    private fun showError(result: Result.Error) {
        _isButtonVisible.value = View.VISIBLE
        _weatherError.value = errorMessageParser.getErrorMessage(result.code ?: -1)
    }


}

interface HomeViewModel {
    val weatherUpdate: LiveData<String>
    val weatherError: LiveData<String>
    val isButtonVisible : LiveData<Int>
    fun onPushed()
}