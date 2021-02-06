package com.gabez.pathvisionapp.domain.usecases

import com.gabez.pathvisionapp.domain.AppRepository

class GetLocalPathsUsecase(private val repo: AppRepository) {
    suspend operator fun invoke() = repo.getLocalPaths()
}