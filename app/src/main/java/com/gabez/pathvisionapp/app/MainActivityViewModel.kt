package com.gabez.pathvisionapp.app

import androidx.lifecycle.ViewModel
import com.gabez.pathvisionapp.statusHolders.CurrentUserHolder

class MainActivityViewModel(private val userHolder: CurrentUserHolder): ViewModel() {
    val currentUser = userHolder.currentUser
}