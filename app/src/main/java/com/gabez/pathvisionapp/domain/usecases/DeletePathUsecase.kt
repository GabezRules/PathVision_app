package com.gabez.pathvisionapp.domain.usecases

import com.gabez.pathvisionapp.domain.AppRepository
import com.gabez.pathvisionapp.domain.entities.PathObject

class DeletePathUsecase(private val repo: AppRepository) {
    suspend operator fun invoke(path: PathObject) = repo.deletePath(path)
}