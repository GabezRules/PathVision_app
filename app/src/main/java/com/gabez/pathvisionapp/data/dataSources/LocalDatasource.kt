package com.gabez.pathvisionapp.data.dataSources

import com.gabez.pathvisionapp.app.paths.entities.SkillStatus
import com.gabez.pathvisionapp.data.localDatabase.entities.PathEntity
import com.gabez.pathvisionapp.data.localDatabase.entities.SkillEntity

interface LocalDatasource {
    suspend fun addPath(path: PathEntity)
    suspend fun deletePath(path: PathEntity)
    suspend fun getAllPaths(): List<PathEntity>
    suspend fun getAllSkills(): List<SkillEntity>
    suspend fun updateSkillStatus(skill: String, newStatus: SkillStatus)
}