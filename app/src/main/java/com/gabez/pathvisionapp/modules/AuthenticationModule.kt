package com.gabez.pathvisionapp.modules

import com.gabez.authentication.authentication.AuthenticationAdapterImpl
import com.gabez.authentication.authentication.PostLoginCompare
import com.gabez.pathvisionapp.data.gateways.AuthenticationAdapter
import com.gabez.pathvisionapp.app.statusHolders.AuthErrorHolder
import com.gabez.pathvisionapp.app.statusHolders.AuthLoadingHolder
import com.gabez.pathvisionapp.app.statusHolders.CurrentUserHolder
import org.koin.dsl.module

val authenticationModule = module{
    single { AuthenticationAdapterImpl(get(), get(), get(), get(), get()) as AuthenticationAdapter }

    single { CurrentUserHolder() }

    single { AuthErrorHolder() }
    single { AuthLoadingHolder() }

    single { PostLoginCompare(get(), get()) }
}