package com.gabez.pathvisionapp.domain.usecases

import com.gabez.pathvisionapp.app.paths.entities.PathForView
import com.gabez.pathvisionapp.app.search.entities.PathForSearch
import com.gabez.pathvisionapp.domain.AppRepository

class DeletePathUsecase(private val repo: AppRepository) {
    //TODO: Add interface for path object, so invoke() can be only one function
    suspend operator fun invoke(path: PathForSearch) = repo.deletePath(path.toPathEntity())
    suspend operator fun invoke(path: PathForView) = repo.deletePath(path.toPathEntity())
}