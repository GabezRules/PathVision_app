package com.gabez.pathvisionapp.domain.usecases

import com.gabez.pathvisionapp.app.search.entities.PathForSearch
import com.gabez.pathvisionapp.domain.AppRepository

class AddPathUsecase(private val repo: AppRepository) {
    suspend operator fun invoke(path: PathForSearch) = repo.addPath(path.toPathEntity())
}