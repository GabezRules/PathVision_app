package com.gabez.pathvisionapp.domain.usecases

import com.gabez.pathvisionapp.app.paths.entities.SkillForView
import com.gabez.pathvisionapp.app.paths.entities.SkillStatus
import com.gabez.pathvisionapp.domain.AppRepository

class UpdateSkillStatusUsecase(private val repo: AppRepository) {
    suspend operator fun invoke(skill: SkillForView, newStatus: SkillStatus) = repo.updateSkillStatus(skill.title, newStatus)
}