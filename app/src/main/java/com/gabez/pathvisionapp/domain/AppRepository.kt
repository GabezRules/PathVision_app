package com.gabez.pathvisionapp.domain

import com.gabez.pathvisionapp.app.paths.entities.SkillStatus
import com.gabez.pathvisionapp.data.localDatabase.entities.PathEntity
import com.gabez.pathvisionapp.data.localDatabase.entities.SkillEntity
import com.gabez.data.remoteApiDatabase.entities.PathFromServer

interface AppRepository {
    suspend fun addPath(path: PathEntity)
    suspend fun deletePath(path: PathEntity)
    suspend fun getLocalPaths(): List<PathEntity>
    suspend fun getLocalSkills(): List<SkillEntity>
    suspend fun updateSkillStatus(skill: String, newStatus: SkillStatus)
    suspend fun getRemotePaths()
    suspend fun getRemoteSkills()
    suspend fun searchPathByKeyword(keyword: String)
    suspend fun searchPathBySkill(skill: String)
}