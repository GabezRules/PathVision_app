package com.gabez.pathvisionapp.app

import androidx.lifecycle.ViewModel
import com.gabez.authentication.authentication.statusHolders.CurrentUserHolder

class MainActivityViewModel(private val userHolder: CurrentUserHolder): ViewModel() {
    val currentUser = userHolder.currentUser
}