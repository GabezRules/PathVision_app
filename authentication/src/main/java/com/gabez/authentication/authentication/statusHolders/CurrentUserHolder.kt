package com.gabez.authentication.authentication.statusHolders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gabez.authentication.authentication.entities.UserObj

class CurrentUserHolder {
    private val _currentUser: MutableLiveData<UserObj?> = MutableLiveData(null)
    val currentUser: LiveData<UserObj?> = _currentUser

    private val _isLoggedIn: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoggedIn: LiveData<Boolean> = _isLoggedIn

    fun setCurrentUser(user: UserObj?){
        _currentUser.postValue(user)
        _isLoggedIn.postValue(user!=null)
    }
}