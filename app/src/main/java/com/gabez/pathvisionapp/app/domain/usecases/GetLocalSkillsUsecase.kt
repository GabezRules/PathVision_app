package com.gabez.pathvisionapp.app.domain.usecases

import com.gabez.pathvisionapp.app.domain.AppRepository

class GetLocalSkillsUsecase(private val repo: AppRepository) {
    suspend operator fun invoke() = repo.getLocalSkills()
}