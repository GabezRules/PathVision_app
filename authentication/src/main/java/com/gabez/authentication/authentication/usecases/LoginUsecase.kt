package com.gabez.authentication.authentication.usecases

import com.gabez.authentication.authentication.AuthenticationAdapter

class LoginUsecase(private val authAdapter: AuthenticationAdapter) {
    operator fun invoke(email: String, password: String) = authAdapter.loginUser(email, password)
}