package com.gabez.pathvisionapp.data.dataSources

import com.gabez.pathvisionapp.entities.PathObject
import com.gabez.pathvisionapp.entities.SkillObject

interface FirebaseDatasource {
    fun addPath(path: PathObject)
    fun deletePath(path: PathObject)

    fun addSkill(skill: SkillObject)
    fun removeSkill(skill: SkillObject)

    fun updateSkillStatus(skill: SkillObject)

    fun getRemotePaths()
    fun getRemoteSkills()
}