package com.gabez.pathvisionapp

import com.gabez.pathvisionapp.app.paths.view.MainViewModel
import com.gabez.pathvisionapp.app.search.view.SearchViewModel
import com.gabez.pathvisionapp.data.dataSources.ApiDatasource
import com.gabez.pathvisionapp.data.dataSources.ApiDatasourceImpl
import com.gabez.pathvisionapp.data.dataSources.LocalDatasource
import com.gabez.pathvisionapp.data.dataSources.LocalDatasourceImpl
import com.gabez.pathvisionapp.data.localDatabase.dbLogic.LocalDatabase
import com.gabez.pathvisionapp.data.repo.AppRepositoryImpl
import com.gabez.pathvisionapp.domain.AppRepository
import com.gabez.pathvisionapp.domain.usecases.AddPathUsecase
import com.gabez.pathvisionapp.domain.usecases.DeletePathUsecase
import com.gabez.pathvisionapp.domain.usecases.GetLocalPathsUsecase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MainViewModel() }
    viewModel { SearchViewModel(get(), get(), get()) }

    single { LocalDatabase.getInstance(get()) }

    single { ApiDatasourceImpl() as ApiDatasource }
    single { LocalDatasourceImpl(get()) as LocalDatasource }

    single { AppRepositoryImpl(get(), get()) as AppRepository }

    single { DeletePathUsecase(get()) }
    single { AddPathUsecase(get()) }
    single { GetLocalPathsUsecase(get()) }
}