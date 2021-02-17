package com.gabez.pathvisionapp.domain.usecases

import com.gabez.pathvisionapp.app.search.entities.PathForSearch
import com.gabez.pathvisionapp.domain.AppRepository
import com.gabez.pathvisionapp.entities.PathObject

class AddPathUsecase(private val repo: AppRepository) {
    suspend operator fun invoke(path: PathObject) = repo.addPath(path)
}