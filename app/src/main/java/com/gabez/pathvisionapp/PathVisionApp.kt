package com.gabez.pathvisionapp

import android.app.Application
import android.content.Context
import com.gabez.pathvisionapp.app.modules.appModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin

class PathVisionApp: Application(), KoinComponent {
    @ExperimentalCoroutinesApi
    @InternalCoroutinesApi
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    @ExperimentalCoroutinesApi
    @InternalCoroutinesApi
    private fun initKoin() {
        startKoin {
            androidContext(this@PathVisionApp)
            modules(appModule)
        }
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
    }
}