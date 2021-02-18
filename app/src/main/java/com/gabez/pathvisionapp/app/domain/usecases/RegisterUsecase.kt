package com.gabez.pathvisionapp.app.domain.usecases

import com.gabez.pathvisionapp.authentication.authentication.authLogic.AuthenticationAdapter

class RegisterUsecase(private val authAdapter: AuthenticationAdapter) {
    operator fun invoke(email: String, username: String, password: String) = authAdapter.registerUser(email, username, password)
}