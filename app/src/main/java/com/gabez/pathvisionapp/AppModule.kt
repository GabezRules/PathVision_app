package com.gabez.pathvisionapp

import com.gabez.pathvisionapp.app.MainActivityViewModel
import com.gabez.pathvisionapp.app.paths.view.MainViewModel
import com.gabez.pathvisionapp.app.search.view.SearchViewModel
import com.gabez.pathvisionapp.app.settings.authentication.login.LoginViewModel
import com.gabez.pathvisionapp.app.settings.authentication.register.RegisterViewModel
import com.gabez.pathvisionapp.app.settings.settingsWithoutAuth.SettingsViewModel
import com.gabez.pathvisionapp.data.dataSources.*
import com.gabez.pathvisionapp.data.dataHolders.DbPathsHolder
import com.gabez.data.remoteApiDatabase.ApiPathsHolder
import com.gabez.data.remoteApiDatabase.NetworkClient
import com.gabez.data.remoteFirebaseDatabase.FirebaseDataHolder
import com.gabez.data.remoteFirebaseDatabase.dbLogic.FirebaseDbAdapter
import com.gabez.data.remoteFirebaseDatabase.dbLogic.FirebaseDbAdapterImpl
import com.gabez.pathvisionapp.data.repo.AppRepositoryImpl
import com.gabez.pathvisionapp.domain.AppRepository
import com.gabez.pathvisionapp.domain.usecases.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MainViewModel(get(), get(), get(), get(), get(), get()) }
    viewModel { SearchViewModel(get(), get(), get(), get(), get(), get(), get()) }

    viewModel { SettingsViewModel(get(), get(), get()) }

    viewModel { LoginViewModel(get(), get(), get(), get()) }
    viewModel { RegisterViewModel(get(), get(), get(), get()) }

    viewModel { MainActivityViewModel(get()) }

    single { LocalDatabase.getInstance(get()) }

    single { ApiDatasourceImpl(get()) as ApiDatasource }
    single { LocalDatasourceImpl(get(), get()) as LocalDatasource }
    single { FirebaseDatasourceImpl(get()) as FirebaseDatasource }

    single { AppRepositoryImpl(get(), get(), get()) as AppRepository }

    single { FirebaseDbAdapterImpl(get(), get(), get()) as FirebaseDbAdapter }

    single { DeletePathUsecase(get()) }
    single { AddPathUsecase(get()) }
    single { GetLocalPathsUsecase(get()) }
    single { GetLocalSkillsUsecase(get()) }
    single { UpdateSkillStatusUsecase(get()) }

    single { DbPathsHolder() }

    single { FirebaseDatabase.getInstance() }
    single { FirebaseAuth.getInstance() }

    single { FirebaseDataHolder() }

    single { NetworkClient(get()) }

    single { ApiPathsHolder() }

    single { SearchPathByKeywordUsecase(get()) }
    single { SearchPathBySkillUsecase(get()) }
}