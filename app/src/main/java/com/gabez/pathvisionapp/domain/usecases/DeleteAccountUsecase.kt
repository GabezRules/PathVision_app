package com.gabez.pathvisionapp.domain.usecases

import com.gabez.pathvisionapp.data.gateways.AuthenticationAdapter

class DeleteAccountUsecase(private val authAdapter: AuthenticationAdapter) {
    operator fun invoke() = authAdapter.deleteUser()
}