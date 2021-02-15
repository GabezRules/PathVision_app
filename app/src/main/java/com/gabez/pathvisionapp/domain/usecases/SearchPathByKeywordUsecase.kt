package com.gabez.pathvisionapp.domain.usecases

import com.gabez.pathvisionapp.domain.AppRepository

class SearchPathByKeywordUsecase(private val repo: AppRepository) {
    suspend operator fun invoke(keyword: String) = repo.searchPathByKeyword(keyword)
}