package com.gabez.pathvisionapp.app.statusHolders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ApiErrorHolder {
    private val _error: MutableLiveData<String> = MutableLiveData("")
    val error: LiveData<String> = _error

    val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)

    fun setError(error: String){
        _error.postValue(error)
    }
}