package com.gabez.authentication.authentication

import com.gabez.authentication.authentication.statusHolders.AuthErrorHolder
import com.gabez.authentication.authentication.statusHolders.AuthLoadingHolder
import com.gabez.authentication.authentication.statusHolders.CurrentUserHolder
import com.gabez.authentication.authentication.usecases.DeleteAccountUsecase
import com.gabez.authentication.authentication.usecases.LoginUsecase
import com.gabez.authentication.authentication.usecases.LogoutUsecase
import com.gabez.authentication.authentication.usecases.RegisterUsecase
import org.koin.dsl.module

val authenticationModule = module{
    single { AuthenticationAdapter(get(), get(), get(), get(), get()) }

    single { CurrentUserHolder() }
    single { AuthErrorHolder() }
    single { AuthLoadingHolder() }

    single { LoginUsecase(get()) }
    single { RegisterUsecase(get()) }
    single { LogoutUsecase(get()) }
    single { DeleteAccountUsecase(get()) }

    single { PostLoginCompare(get(), get()) }
}