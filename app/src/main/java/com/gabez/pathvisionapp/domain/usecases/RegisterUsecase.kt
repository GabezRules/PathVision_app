package com.gabez.pathvisionapp.domain.usecases

import com.gabez.pathvisionapp.data.gateways.AuthenticationAdapter

class RegisterUsecase(private val authAdapter: AuthenticationAdapter) {
    operator fun invoke(email: String, username: String, password: String) = authAdapter.registerUser(email, username, password)
}