package com.gabez.pathvisionapp.authentication.usecases

import com.gabez.pathvisionapp.authentication.AuthenticationAdapter

class LoginUsecase(private val authAdapter: AuthenticationAdapter) {
    operator fun invoke(email: String, password: String) = authAdapter.loginUser(email, password)
}