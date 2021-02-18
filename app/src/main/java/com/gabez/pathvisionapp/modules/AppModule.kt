package com.gabez.pathvisionapp.modules

import com.gabez.pathvisionapp.dataModule.localDatabase.gateway.LocalDbGatewayImpl
import com.gabez.pathvisionapp.dataModule.remoteApiDatabase.gateway.ApiGatewayImpl
import com.gabez.pathvisionapp.dataModule.remoteFirebaseDatabase.gateway.FirebaseGatewayImpl
import com.gabez.pathvisionapp.app.MainActivityViewModel
import com.gabez.pathvisionapp.app.paths.view.MainViewModel
import com.gabez.pathvisionapp.app.search.view.SearchViewModel
import com.gabez.pathvisionapp.app.settings.authentication.login.LoginViewModel
import com.gabez.pathvisionapp.app.settings.authentication.register.RegisterViewModel
import com.gabez.pathvisionapp.app.settings.settingsWithoutAuth.SettingsViewModel
import com.gabez.pathvisionapp.authentication.authentication.authLogic.AuthenticationAdapter
import com.gabez.pathvisionapp.authentication.authentication.authLogic.AuthenticationAdapterImpl
import com.gabez.pathvisionapp.authentication.authentication.statusHolders.AuthErrorHolder
import com.gabez.pathvisionapp.authentication.authentication.statusHolders.AuthLoadingHolder
import com.gabez.pathvisionapp.authentication.authentication.statusHolders.CurrentUserHolder
import com.gabez.pathvisionapp.dataModule.remoteApiDatabase.statusHolders.ApiErrorHolder
import com.gabez.pathvisionapp.data.dataSources.*
import com.gabez.pathvisionapp.dataModule.remoteApiDatabase.gateway.ApiGateway
import com.gabez.pathvisionapp.dataModule.remoteFirebaseDatabase.gateway.FirebaseGateway
import com.gabez.pathvisionapp.dataModule.localDatabase.gateway.LocalDbGateway
import com.gabez.pathvisionapp.data.repo.AppRepositoryImpl
import com.gabez.pathvisionapp.dataApp.dataSources.LocalDatasourceImpl
import com.gabez.pathvisionapp.dataModule.localDatabase.dbLogic.LocalDatabase
import com.gabez.pathvisionapp.dataModule.remoteApiDatabase.apiLogic.NetworkClient
import com.gabez.pathvisionapp.dataModule.remoteFirebaseDatabase.dbLogic.FirebaseDbAdapter
import com.gabez.pathvisionapp.dataModule.remoteFirebaseDatabase.dbLogic.FirebaseDbAdapterImpl
import com.gabez.pathvisionapp.domain.AppRepository
import com.gabez.pathvisionapp.domain.usecases.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
val appModule = module(override = true) {
    viewModel { MainViewModel(get(), get(), get(), get(), get()) }
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
    single { LocalDbGatewayImpl(get()) as LocalDbGateway }

    single { ApiErrorHolder() }

    //usecases

    single { DeletePathUsecase(get()) }
    single { AddPathUsecase(get()) }
    single { GetLocalPathsUsecase(get()) }
    single { GetLocalSkillsUsecase(get()) }
    single { UpdateSkillStatusUsecase(get()) }
    single { SearchPathByKeywordUsecase(get()) }
    single { SearchPathBySkillUsecase(get()) }

    single { DeleteAccountUsecase(get()) }
    single { LoginUsecase(get()) }
    single { LogoutUsecase(get()) }
    single { RegisterUsecase(get()) }

    //authentication

    single { AuthenticationAdapterImpl(get(), get(), get(), get()) as AuthenticationAdapter }

    single { CurrentUserHolder() }

    single { AuthErrorHolder() }
    single { AuthLoadingHolder() }

    //data

    single { LocalDatabase.getInstance(get()) }

    single { NetworkClient(get()) }
    single { ApiErrorHolder() }

    single { FirebaseDbAdapterImpl() as FirebaseDbAdapter }
}