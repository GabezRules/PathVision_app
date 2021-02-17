package com.gabez.pathvisionapp.data.gateways

import com.gabez.pathvisionapp.entities.PathObject
import com.gabez.pathvisionapp.entities.SkillObject

interface FirebaseGateway {
    fun addPath(path: PathObject)
    fun deletePath(path: PathObject)
    fun addSkill(skill: SkillObject)
    fun deleteskill(skill: SkillObject)
    fun updateSkillStatus(skill: SkillObject)
    fun getRemotePaths()
    fun getRemoteSkills()
}