package com.gabez.pathvisionapp.app.settings.authentication.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gabez.pathvisionapp.authentication.AuthErrorHolder
import com.gabez.pathvisionapp.authentication.AuthLoadingHolder
import com.gabez.pathvisionapp.authentication.usecases.LoginUsecase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginViewModel(private val errorHolder: AuthErrorHolder, private val loadingStateHolder: AuthLoadingHolder, private val login: LoginUsecase) :
    ViewModel() {
    val authenticationError: LiveData<String?> = errorHolder.authError

    private val _loginEmailError: MutableLiveData<String?> = MutableLiveData(null)
    val loginEmailError: LiveData<String?> = _loginEmailError

    private val _loginPasswordError: MutableLiveData<String?> = MutableLiveData(null)
    val loginPasswordError: LiveData<String?> = _loginPasswordError

    val isLoading: LiveData<Boolean> = loadingStateHolder.authenticationInProgress

    fun loginUser(email: String, password: String) {
        if (isEmailValid(email)) {
            if (!password.isNullOrBlank()) {
                GlobalScope.launch(Dispatchers.IO) { login.invoke(email, password) }
            } else _loginPasswordError.postValue("Enter a valid password!")

        } else _loginEmailError.postValue("Enter a valid email!")
    }

    private fun isEmailValid(email: CharSequence?): Boolean {
        if (email.isNullOrBlank()) return false
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}