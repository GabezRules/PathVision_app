package com.gabez.pathvisionapp.modules

import com.gabez.pathvisionapp.data.gateways.AuthenticationAdapter
import com.gabez.pathvisionapp.statusHolders.AuthErrorHolder
import com.gabez.pathvisionapp.statusHolders.AuthLoadingHolder
import com.gabez.pathvisionapp.statusHolders.CurrentUserHolder
import org.koin.dsl.module

val authenticationModule = module{
    single { AuthenticationAdapterImpl(get(), get(), get(), get(), get()) as AuthenticationAdapter }

    single { CurrentUserHolder() }

    single { AuthErrorHolder() }
    single { AuthLoadingHolder() }

    single { PostLoginCompare(get(), get()) }
}