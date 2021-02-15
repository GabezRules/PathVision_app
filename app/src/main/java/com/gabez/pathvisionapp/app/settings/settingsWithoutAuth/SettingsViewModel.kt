package com.gabez.pathvisionapp.app.settings.settingsWithoutAuth

import androidx.lifecycle.ViewModel

class SettingsViewModel(private val userHolder: CurrentUserHolder, private val logout: LogoutUsecase, private val deleteAccount: DeleteAccountUsecase): ViewModel() {
    val currentUser = userHolder.currentUser

    fun logout() = logout.invoke()
    fun deleteAccount() = deleteAccount.invoke()
}