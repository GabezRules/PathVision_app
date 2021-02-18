package com.gabez.pathvisionapp.app.domain.usecases

import com.gabez.pathvisionapp.app.domain.AppRepository
import com.gabez.pathvisionapp.app.domain.entities.SkillObject

class UpdateSkillStatusUsecase(private val repo: AppRepository) {
    suspend operator fun invoke(skill: SkillObject) = repo.updateSkillStatus(skill)
}