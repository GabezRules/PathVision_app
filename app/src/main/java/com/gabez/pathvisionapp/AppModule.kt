package com.gabez.pathvisionapp

import com.gabez.pathvisionapp.app.MainActivityViewModel
import com.gabez.pathvisionapp.app.paths.view.MainViewModel
import com.gabez.pathvisionapp.app.search.view.SearchViewModel
import com.gabez.pathvisionapp.app.settings.authentication.login.LoginViewModel
import com.gabez.pathvisionapp.app.settings.authentication.register.RegisterViewModel
import com.gabez.pathvisionapp.app.settings.settingsWithoutAuth.SettingsViewModel
import com.gabez.pathvisionapp.authentication.AuthenticationAdapter
import com.gabez.pathvisionapp.authentication.PostLoginCompare
import com.gabez.pathvisionapp.authentication.statusHolders.AuthErrorHolder
import com.gabez.pathvisionapp.authentication.statusHolders.AuthLoadingHolder
import com.gabez.pathvisionapp.authentication.statusHolders.CurrentUserHolder
import com.gabez.pathvisionapp.authentication.usecases.DeleteAccountUsecase
import com.gabez.pathvisionapp.authentication.usecases.LoginUsecase
import com.gabez.pathvisionapp.authentication.usecases.LogoutUsecase
import com.gabez.pathvisionapp.authentication.usecases.RegisterUsecase
import com.gabez.pathvisionapp.data.dataSources.*
import com.gabez.pathvisionapp.data.localDatabase.DbPathsHolder
import com.gabez.pathvisionapp.data.localDatabase.dbLogic.LocalDatabase
import com.gabez.pathvisionapp.data.remoteApiDatabase.ApiPathsHolder
import com.gabez.pathvisionapp.data.remoteApiDatabase.NetworkClient
import com.gabez.pathvisionapp.data.remoteFirebaseDatabase.FirebaseDataHolder
import com.gabez.pathvisionapp.data.remoteFirebaseDatabase.dbLogic.FirebaseDbAdapter
import com.gabez.pathvisionapp.data.remoteFirebaseDatabase.dbLogic.FirebaseDbAdapterImpl
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

    single { AuthenticationAdapter(get(), get(), get(), get(), get()) }

    single { CurrentUserHolder() }
    single { AuthErrorHolder() }
    single { AuthLoadingHolder() }

    single { LoginUsecase(get()) }
    single { RegisterUsecase(get()) }
    single { LogoutUsecase(get()) }
    single { DeleteAccountUsecase(get()) }

    single { FirebaseDatabase.getInstance() }
    single { FirebaseAuth.getInstance() }

    single { PostLoginCompare(get(), get()) }
    single { FirebaseDataHolder() }

    single { NetworkClient(get()) }

    single { ApiPathsHolder() }

    single { SearchPathByKeywordUsecase(get()) }
    single { SearchPathBySkillUsecase(get()) }
}