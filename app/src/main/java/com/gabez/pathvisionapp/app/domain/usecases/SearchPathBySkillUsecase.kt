package com.gabez.pathvisionapp.app.domain.usecases

import com.gabez.pathvisionapp.app.domain.AppRepository

class SearchPathBySkillUsecase(private val repo: AppRepository) {
    suspend operator fun invoke(skill: String) = repo.searchPathBySkill(skill)
}