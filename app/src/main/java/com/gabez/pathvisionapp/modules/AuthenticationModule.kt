package com.gabez.pathvisionapp.modules

import com.gabez.authentication.authentication.AuthenticationAdapter
import com.gabez.pathvisionapp.PostLoginCompare
import com.gabez.authentication.authentication.statusHolders.AuthErrorHolder
import com.gabez.authentication.authentication.statusHolders.AuthLoadingHolder
import com.gabez.authentication.authentication.statusHolders.CurrentUserHolder
import org.koin.dsl.module

val authenticationModule = module{
    single { AuthenticationAdapter(get(), get(), get(), get(), get()) }

    single { CurrentUserHolder() }

    single { AuthErrorHolder() }
    single { AuthLoadingHolder() }

    single { PostLoginCompare(get(), get()) }
}