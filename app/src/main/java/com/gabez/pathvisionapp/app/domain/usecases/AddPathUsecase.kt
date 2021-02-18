package com.gabez.pathvisionapp.app.domain.usecases

import com.gabez.pathvisionapp.app.domain.AppRepository
import com.gabez.pathvisionapp.app.domain.entities.PathObject

class AddPathUsecase(private val repo: AppRepository) {
    suspend operator fun invoke(path: PathObject) = repo.addPath(path)
}