package com.gabez.pathvisionapp

import com.gabez.pathvisionapp.app.paths.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MainViewModel() }
}