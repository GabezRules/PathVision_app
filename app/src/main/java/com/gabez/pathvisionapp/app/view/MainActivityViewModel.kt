package com.gabez.pathvisionapp.app.view

import androidx.lifecycle.ViewModel
import com.gabez.pathvisionapp.authentication.authentication.statusHolders.CurrentUserHolder

class MainActivityViewModel(private val userHolder: CurrentUserHolder): ViewModel() {
    val currentUser = userHolder.currentUser
}