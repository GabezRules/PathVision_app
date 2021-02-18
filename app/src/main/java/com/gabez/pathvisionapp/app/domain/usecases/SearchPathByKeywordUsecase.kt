package com.gabez.pathvisionapp.app.domain.usecases

import com.gabez.pathvisionapp.app.domain.AppRepository

class SearchPathByKeywordUsecase(private val repo: AppRepository) {
    suspend operator fun invoke(keyword: String) = repo.searchPathByKeyword(keyword)
}