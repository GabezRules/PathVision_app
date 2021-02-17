package com.gabez.pathvisionapp.modules

import com.gabez.pathvisionapp.app.MainActivityViewModel
import com.gabez.pathvisionapp.app.paths.view.MainViewModel
import com.gabez.pathvisionapp.app.search.view.SearchViewModel
import com.gabez.pathvisionapp.app.settings.authentication.login.LoginViewModel
import com.gabez.pathvisionapp.app.settings.authentication.register.RegisterViewModel
import com.gabez.pathvisionapp.app.settings.settingsWithoutAuth.SettingsViewModel
import com.gabez.pathvisionapp.data.dataHolders.ApiPathsHolder
import com.gabez.pathvisionapp.data.dataHolders.DbPathsHolder
import com.gabez.pathvisionapp.data.dataHolders.FirebaseDataHolder
import com.gabez.pathvisionapp.data.dataSources.*
import com.gabez.pathvisionapp.data.gateways.ApiGateway
import com.gabez.pathvisionapp.data.gateways.FirebaseGateway
import com.gabez.pathvisionapp.data.gateways.LocalDbGateway
import com.gabez.pathvisionapp.data.repo.AppRepositoryImpl
import com.gabez.pathvisionapp.domain.AppRepository
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@InternalCoroutinesApi
val appModule = module {
    viewModel { MainViewModel(get(), get(), get(), get(), get(), get()) }
    viewModel { SearchViewModel(get(), get(), get(), get(), get(), get(), get()) }
    viewModel { SettingsViewModel(get(), get(), get()) }

    viewModel { LoginViewModel(get(), get(), get(), get()) }
    viewModel { RegisterViewModel(get(), get(), get(), get()) }

    viewModel { MainActivityViewModel(get()) }

    single { AppRepositoryImpl(get(), get(), get()) as AppRepository }
    single { LocalDatasourceImpl(get()) as LocalDatasource }
    single { FirebaseDatasourceImpl(get()) as FirebaseDatasource }
    single { ApiDatasourceImpl(get()) as ApiDatasource }

    single { ApiGatewayImpl(get()) as ApiGateway }
    single { FirebaseGatewayImpl(get()) as FirebaseGateway }
    single { LocalDbGatewayImpl(get(), get()) as LocalDbGateway }

    single { DbPathsHolder() }
    single { ApiPathsHolder() }
    single { FirebaseDataHolder() }
}