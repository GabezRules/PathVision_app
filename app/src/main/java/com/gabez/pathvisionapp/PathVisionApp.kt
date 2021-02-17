package com.gabez.pathvisionapp

import android.app.Application
import android.content.Context
import com.gabez.pathvisionapp.modules.authenticationModule
import com.gabez.pathvisionapp.modules.appModule
import com.gabez.pathvisionapp.modules.dataModule
import com.gabez.pathvisionapp.modules.useCases
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin

class PathVisionApp: Application(), KoinComponent {
    @InternalCoroutinesApi
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    @InternalCoroutinesApi
    private fun initKoin() {
        startKoin {
            androidContext(this@PathVisionApp)
            modules(appModule, authenticationModule, dataModule, useCases)
        }
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
    }
}