package com.gabez.pathvisionapp.dataModule.localDatabase.gateway

import com.gabez.pathvisionapp.domain.entities.PathObject
import com.gabez.pathvisionapp.domain.entities.SkillObject
import kotlinx.coroutines.flow.Flow

interface LocalDbGateway {
    suspend fun addPath(path: PathObject)
    suspend fun deletePath(path: PathObject)
    suspend fun getAllPaths(): Flow<List<PathObject>>
    suspend fun getAllSkills(): Flow<List<SkillObject>>
    suspend fun updateSkillStatus(skill: SkillObject)
}