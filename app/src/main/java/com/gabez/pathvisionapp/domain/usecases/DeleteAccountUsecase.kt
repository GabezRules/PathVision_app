package com.gabez.pathvisionapp.domain.usecases

import com.gabez.authentication.authentication.AuthenticationAdapter

class DeleteAccountUsecase(private val authAdapter: AuthenticationAdapter) {
    operator fun invoke() = authAdapter.deleteUser()
}