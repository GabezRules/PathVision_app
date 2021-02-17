package com.gabez.pathvisionapp.data.gateways

import com.gabez.pathvisionapp.domain.entities.PathObject
import com.gabez.pathvisionapp.domain.entities.SkillObject
import kotlinx.coroutines.flow.Flow

interface FirebaseGateway {
    fun addPath(path: PathObject)
    fun deletePath(path: PathObject)
    fun addSkill(skill: SkillObject)
    fun deleteskill(skill: SkillObject)
    fun updateSkillStatus(skill: SkillObject)
    fun getRemotePaths(): Flow<PathObject>
    fun getRemoteSkills(): Flow<SkillObject>
}