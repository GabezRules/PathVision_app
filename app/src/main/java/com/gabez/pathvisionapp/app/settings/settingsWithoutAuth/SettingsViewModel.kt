package com.gabez.pathvisionapp.app.settings.settingsWithoutAuth

import androidx.lifecycle.ViewModel
import com.gabez.pathvisionapp.authentication.CurrentUserHolder

class SettingsViewModel(private val userHolder: CurrentUserHolder): ViewModel() {
    val currentUser = userHolder.currentUser
}