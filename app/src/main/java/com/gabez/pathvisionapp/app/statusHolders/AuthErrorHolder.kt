package com.gabez.pathvisionapp.app.statusHolders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class AuthErrorHolder {
    private val _authError: MutableLiveData<String?> = MutableLiveData()
    val authError: LiveData<String?> = _authError

    fun setAuthError(error: String?) = _authError.postValue(error)
}