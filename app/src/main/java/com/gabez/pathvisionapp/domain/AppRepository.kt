package com.gabez.pathvisionapp.domain

import com.gabez.pathvisionapp.data.localDatabase.entities.PathEntity
import com.gabez.pathvisionapp.data.localDatabase.entities.SkillEntity

interface AppRepository {
    suspend fun addPath(path: PathEntity)
    suspend fun deletePath(path: PathEntity)
    suspend fun getLocalPaths(): List<PathEntity>
    suspend fun getLocalSkills(): List<SkillEntity>
}