package com.gabez.pathvisionapp

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin

class PathVisionApp: Application(), KoinComponent {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@PathVisionApp)
            modules(appModule)
        }
    }
}