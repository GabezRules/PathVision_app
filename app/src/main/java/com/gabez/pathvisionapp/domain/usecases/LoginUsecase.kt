package com.gabez.pathvisionapp.domain.usecases

import com.gabez.pathvisionapp.data.gateways.AuthenticationAdapter

class LoginUsecase(private val authAdapter: AuthenticationAdapter) {
    operator fun invoke(email: String, password: String) = authAdapter.loginUser(email, password)
}