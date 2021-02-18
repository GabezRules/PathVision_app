package com.gabez.pathvisionapp.app.domain

import com.gabez.pathvisionapp.app.domain.entities.PathObject
import com.gabez.pathvisionapp.app.domain.entities.SkillObject
import kotlinx.coroutines.flow.Flow

interface AppRepository {
    suspend fun addPath(path: PathObject)
    suspend fun deletePath(path: PathObject)

    suspend fun getLocalPaths(): Flow<List<PathObject>>
    suspend fun getLocalSkills(): Flow<List<SkillObject>>

    suspend fun updateSkillStatus(skill: SkillObject)

    suspend fun getRemotePaths(): Flow<PathObject>
    suspend fun getRemoteSkills(): Flow<SkillObject>

    suspend fun searchPathByKeyword(keyword: String): Flow<List<PathObject>>
    suspend fun searchPathBySkill(skill: String): Flow<List<PathObject>>
}