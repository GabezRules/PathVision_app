package com.gabez.pathvisionapp.app.settings.authentication.register

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gabez.pathvisionapp.authentication.AuthErrorHolder
import com.gabez.pathvisionapp.authentication.AuthLoadingHolder
import com.gabez.pathvisionapp.authentication.usecases.RegisterUsecase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val errorHolder: AuthErrorHolder,
    private val loadingStateHolder: AuthLoadingHolder,
    private val register: RegisterUsecase
) :
    ViewModel() {
    val authenticationError: LiveData<String?> = errorHolder.authError

    private val _registerEmailError: MutableLiveData<String?> = MutableLiveData(null)
    val registerEmailError: LiveData<String?> = _registerEmailError

    private val _registerPasswordError: MutableLiveData<String?> = MutableLiveData(null)
    val registerPasswordError: LiveData<String?> = _registerPasswordError

    private val _registerUsernameError: MutableLiveData<String?> = MutableLiveData(null)
    val registerUsernameError: LiveData<String?> = _registerUsernameError

    val isLoading: LiveData<Boolean> = loadingStateHolder.authenticationInProgress

    fun registerUser(email: String, login: String, password: String) {
        if (isEmailValid(email)) {
            if (!login.isBlank()) {
                if (!password.isBlank()) {
                    GlobalScope.launch(Dispatchers.IO) { register.invoke(email, login, password) }
                } else _registerPasswordError.postValue("enter a valid password")

            } else _registerUsernameError.postValue("enter your name")

        } else _registerEmailError.postValue("enter a valid email")
    }

    private fun isEmailValid(email: CharSequence?): Boolean {
        if (email.isNullOrBlank()) return false
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}