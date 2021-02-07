package com.gabez.pathvisionapp.authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gabez.pathvisionapp.authentication.entities.UserObj

class CurrentUserHolder {
    private val _currentUser: MutableLiveData<UserObj?> = MutableLiveData(null)
    val currentUser: LiveData<UserObj?> = _currentUser

    fun setCurrentUser(user: UserObj?) = _currentUser.postValue(user)
}