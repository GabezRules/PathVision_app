package com.gabez.pathvisionapp.authentication.usecases

import com.gabez.pathvisionapp.authentication.AuthenticationAdapter

class DeleteAccountUsecase(private val authAdapter: AuthenticationAdapter) {
    operator fun invoke() = authAdapter.deleteUser()
}