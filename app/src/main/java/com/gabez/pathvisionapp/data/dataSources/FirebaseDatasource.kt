package com.gabez.pathvisionapp.data.dataSources

import com.gabez.pathvisionapp.app.paths.entities.PathForView
import com.gabez.pathvisionapp.app.paths.entities.SkillForView

interface FirebaseDatasource {
    fun addPath(path: PathForView)
    fun deletePath(name: String)

    fun addSkill(skill: SkillForView)
    fun removeSkill(skill: SkillForView)

    fun updateSkillStatus(skill: SkillForView)

    fun getRemotePaths()
    fun getRemoteSkills()
}