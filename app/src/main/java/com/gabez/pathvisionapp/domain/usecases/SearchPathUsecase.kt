package com.gabez.pathvisionapp.domain.usecases

import com.gabez.pathvisionapp.domain.AppRepository

class SearchPathUsecase(private val repo: AppRepository) {
    suspend operator fun invoke(keyword: String) = repo.searchPath(keyword)
}