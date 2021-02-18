package com.gabez.pathvisionapp.data.dataSources

import com.gabez.pathvisionapp.app.domain.entities.PathObject
import com.gabez.pathvisionapp.app.domain.entities.SkillObject
import kotlinx.coroutines.flow.Flow

interface LocalDatasource {
    suspend fun addPath(path: PathObject)
    suspend fun deletePath(path: PathObject)
    suspend fun getAllPaths(): Flow<List<PathObject>>
    suspend fun getAllSkills(): Flow<List<SkillObject>>
    suspend fun updateSkillStatus(skill: SkillObject)
}