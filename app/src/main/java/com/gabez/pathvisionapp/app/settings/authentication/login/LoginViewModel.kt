package com.gabez.pathvisionapp.app.settings.authentication.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gabez.authentication.authentication.statusHolders.AuthErrorHolder
import com.gabez.authentication.authentication.statusHolders.AuthLoadingHolder
import com.gabez.authentication.authentication.statusHolders.CurrentUserHolder
import com.gabez.authentication.authentication.usecases.LoginUsecase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginViewModel(
    private val errorHolder: AuthErrorHolder,
    private val loadingStateHolder: AuthLoadingHolder,
    private val userHolder: CurrentUserHolder,
    private val login: LoginUsecase
) :
    ViewModel() {
    val authenticationError: LiveData<String?> = errorHolder.authError

    private val _loginEmailError: MutableLiveData<String?> = MutableLiveData(null)
    val loginEmailError: LiveData<String?> = _loginEmailError

    private val _loginPasswordError: MutableLiveData<String?> = MutableLiveData(null)
    val loginPasswordError: LiveData<String?> = _loginPasswordError

    val isLoading: LiveData<Boolean> = loadingStateHolder.authenticationInProgress

    val isLoggedIn: LiveData<Boolean> = userHolder.isLoggedIn

    fun loginUser(email: String, password: String) {
        if (isEmailValid(email)) {
            if (!password.isBlank()) {
                GlobalScope.launch(Dispatchers.IO) { login.invoke(email, password) }
            } else _loginPasswordError.postValue("Enter a valid password!")

        } else _loginEmailError.postValue("Enter a valid email!")
    }

    private fun isEmailValid(email: CharSequence?): Boolean {
        if (email.isNullOrBlank()) return false
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}