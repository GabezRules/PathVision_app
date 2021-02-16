package com.gabez.pathvisionapp.data.gateways

import com.gabez.pathvisionapp.app.paths.entities.SkillStatus
import com.gabez.pathvisionapp.app.search.entities.PathForSearch
import com.gabez.pathvisionapp.app.search.entities.SkillForSearch

interface LocalDbGateway {
    suspend fun addPath(path: PathForSearch)
    suspend fun deletePath(path: PathForSearch)
    suspend fun getAllPaths(): List<PathForSearch>
    suspend fun getAllSkills(): List<SkillForSearch>
    suspend fun updateSkillStatus(skill: String, newStatus: SkillStatus)
    suspend fun refreshPaths()
}