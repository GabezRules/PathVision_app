package com.gabez.pathvisionapp.authentication.usecases

import com.gabez.pathvisionapp.authentication.AuthenticationAdapter

class RegisterUsecase(private val authAdapter: AuthenticationAdapter) {
    operator fun invoke(email: String, username: String, password: String) = authAdapter.registerUser(email, username, password)
}