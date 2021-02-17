package com.gabez.pathvisionapp.data.dataSources

import com.gabez.pathvisionapp.entities.PathObject
import com.gabez.pathvisionapp.entities.SkillObject

interface LocalDatasource {
    suspend fun addPath(path: PathObject)
    suspend fun deletePath(path: PathObject)
    suspend fun getAllPaths(): List<PathObject>
    suspend fun getAllSkills(): List<SkillObject>
    suspend fun updateSkillStatus(skill: SkillObject)
}