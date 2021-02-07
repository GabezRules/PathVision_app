package com.gabez.pathvisionapp.domain.usecases

import com.gabez.pathvisionapp.domain.AppRepository

class GetLocalSkillsUsecase(private val repo: AppRepository) {
    suspend operator fun invoke() = repo.getLocalSkills()
}