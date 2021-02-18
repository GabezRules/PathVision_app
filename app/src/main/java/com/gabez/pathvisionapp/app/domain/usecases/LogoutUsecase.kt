package com.gabez.pathvisionapp.app.domain.usecases

import com.gabez.pathvisionapp.authentication.authentication.authLogic.AuthenticationAdapter

class LogoutUsecase(private val authAdapter: AuthenticationAdapter) {
    operator fun invoke() = authAdapter.logoutUser()
}