package com.gabez.pathvisionapp.data.dataSources

import com.gabez.pathvisionapp.app.paths.entities.SkillStatus
import com.gabez.pathvisionapp.app.search.entities.PathForSearch
import com.gabez.pathvisionapp.app.search.entities.SkillForSearch

interface LocalDatasource {
    suspend fun addPath(path: PathForSearch)
    suspend fun deletePath(path: PathForSearch)
    suspend fun getAllPaths(): List<PathForSearch>
    suspend fun getAllSkills(): List<SkillForSearch>
    suspend fun updateSkillStatus(skill: String, newStatus: SkillStatus)
}