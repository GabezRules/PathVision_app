package com.gabez.pathvisionapp.modules

import com.gabez.pathvisionapp.domain.usecases.*
import org.koin.dsl.module

val useCases = module {
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
}