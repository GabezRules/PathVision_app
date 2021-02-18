package com.gabez.pathvisionapp.app.domain.usecases

import com.gabez.pathvisionapp.app.domain.AppRepository

class GetLocalPathsUsecase(private val repo: AppRepository) {
    suspend operator fun invoke() = repo.getLocalPaths()
}