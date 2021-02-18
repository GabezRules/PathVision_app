package com.gabez.pathvisionapp.app.domain.usecases

import com.gabez.pathvisionapp.authentication.authentication.authLogic.AuthenticationAdapter

class LoginUsecase(private val authAdapter: AuthenticationAdapter) {
    operator fun invoke(email: String, password: String) = authAdapter.loginUser(email, password)
}