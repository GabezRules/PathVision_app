package com.gabez.pathvisionapp.domain

import com.gabez.pathvisionapp.entities.PathObject
import com.gabez.pathvisionapp.entities.SkillObject

interface AppRepository {
    suspend fun addPath(path: PathObject)
    suspend fun deletePath(path: PathObject)
    suspend fun getLocalPaths(): List<PathObject>
    suspend fun getLocalSkills(): List<SkillObject>
    suspend fun updateSkillStatus(skill: SkillObject)
    suspend fun getRemotePaths()
    suspend fun getRemoteSkills()
    suspend fun searchPathByKeyword(keyword: String)
    suspend fun searchPathBySkill(skill: String)
}