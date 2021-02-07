package com.gabez.pathvisionapp.domain.usecases

import com.gabez.pathvisionapp.data.localDatabase.entities.PathEntity
import com.gabez.pathvisionapp.domain.AppRepository

class DeletePathUsecase(private val repo: AppRepository) {
    suspend operator fun invoke(path: PathEntity) = repo.deletePath(path)
}