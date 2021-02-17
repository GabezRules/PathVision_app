package com.gabez.pathvisionapp.domain.usecases

import com.gabez.pathvisionapp.domain.AppRepository
import com.gabez.pathvisionapp.domain.entities.SkillObject

class UpdateSkillStatusUsecase(private val repo: AppRepository) {
    suspend operator fun invoke(skill: SkillObject) = repo.updateSkillStatus(skill)
}