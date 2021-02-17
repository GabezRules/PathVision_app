package com.gabez.pathvisionapp.domain.usecases

import com.gabez.pathvisionapp.app.paths.entities.SkillForView
import com.gabez.pathvisionapp.app.paths.entities.SkillStatus
import com.gabez.pathvisionapp.domain.AppRepository
import com.gabez.pathvisionapp.entities.SkillObject

class UpdateSkillStatusUsecase(private val repo: AppRepository) {
    suspend operator fun invoke(skill: SkillObject) = repo.updateSkillStatus(skill)
}