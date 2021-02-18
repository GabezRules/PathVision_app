package com.gabez.pathvisionapp.app.view.settings.settingsWithoutAuth

import androidx.lifecycle.ViewModel
import com.gabez.pathvisionapp.app.domain.usecases.DeleteAccountUsecase
import com.gabez.pathvisionapp.app.domain.usecases.LogoutUsecase
import com.gabez.pathvisionapp.authentication.authentication.statusHolders.CurrentUserHolder

class SettingsViewModel(private val userHolder: CurrentUserHolder, private val logout: LogoutUsecase, private val deleteAccount: DeleteAccountUsecase): ViewModel() {
    val currentUser = userHolder.currentUser

    fun logout() = logout.invoke()
    fun deleteAccount() = deleteAccount.invoke()
}