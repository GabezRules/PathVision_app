package com.gabez.pathvisionapp.domain

import com.gabez.pathvisionapp.app.paths.entities.SkillStatus
import com.gabez.data.localDatabase.entities.PathEntity
import com.gabez.data.localDatabase.entities.SkillEntity
import com.gabez.data.remoteApiDatabase.entities.PathFromServer
import com.gabez.pathvisionapp.app.search.entities.PathForSearch
import com.gabez.pathvisionapp.app.search.entities.SkillForSearch

interface AppRepository {
    suspend fun addPath(path: PathForSearch)
    suspend fun deletePath(path: PathForSearch)
    suspend fun getLocalPaths(): List<PathForSearch>
    suspend fun getLocalSkills(): List<SkillForSearch>
    suspend fun updateSkillStatus(skill: String, newStatus: SkillStatus)
    suspend fun getRemotePaths()
    suspend fun getRemoteSkills()
    suspend fun searchPathByKeyword(keyword: String)
    suspend fun searchPathBySkill(skill: String)
}