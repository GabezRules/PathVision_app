package com.gabez.pathvisionapp.domain.usecases

import com.gabez.authentication.authentication.AuthenticationAdapter

class LogoutUsecase(private val authAdapter: AuthenticationAdapter) {
    operator fun invoke() = authAdapter.logoutUser()
}