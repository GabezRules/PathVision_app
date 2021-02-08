package com.gabez.pathvisionapp.authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class AuthLoadingHolder {
    private val _authenticationInProgress: MutableLiveData<Boolean> = MutableLiveData(false)
    val authenticationInProgress: LiveData<Boolean> = _authenticationInProgress

    fun setAuthenticationState(state: Boolean) = _authenticationInProgress.postValue(state)
}