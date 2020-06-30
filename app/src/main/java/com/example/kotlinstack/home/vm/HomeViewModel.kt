package com.example.kotlinstack.home.vm

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.databinding.Bindable
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.kotlinstack.data.models.WeatherModel
import com.example.kotlinstack.data.remote.Result
import com.example.kotlinstack.data.repos.WeatherRepo
import com.example.kotlinstack.utils.ErrorMessageParser
import com.example.kotlinstack.vm.ObservableViewModel
import kotlinx.coroutines.launch


class DefaultHomeViewModel @ViewModelInject constructor(
    private val weatherRepo: WeatherRepo,
    private val errorMessageParser: ErrorMessageParser,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ObservableViewModel(), HomeViewModel {

    private val _weatherUpdate = MutableLiveData<String>()
    override val weatherUpdate: LiveData<String>
        get() = _weatherUpdate

    private val _weatherError = MutableLiveData<String>()
    override val weatherError: LiveData<String>
        get() = _weatherError

    private val _isButtonVisible = MutableLiveData<Int>()
    override val isButtonVisible: LiveData<Int>
        get() = _isButtonVisible


    private val _search = MutableLiveData<String>()
    override val search: LiveData<String>
        get() = _search


    override fun onPushed() {

        viewModelScope.launch {
            when (val result = weatherRepo.getData()) {
                is Result.Success -> showTemperature(result)
                is Result.Error -> showError(result)
            }
        }
    }

    @Bindable
    override fun getSearchListener(): TextWatcher {
        return object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                _search.value = p0.toString()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

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
    val search: LiveData<String>
    val isButtonVisible: LiveData<Int>
    fun getSearchListener(): TextWatcher
    fun onPushed()
}