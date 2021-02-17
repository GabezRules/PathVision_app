package com.gabez.pathvisionapp.data.gateways

import com.gabez.pathvisionapp.app.paths.entities.SkillStatus
import com.gabez.pathvisionapp.app.search.entities.PathForSearch
import com.gabez.pathvisionapp.app.search.entities.SkillForSearch
import com.gabez.pathvisionapp.entities.PathObject
import com.gabez.pathvisionapp.entities.SkillObject

interface LocalDbGateway {
    suspend fun addPath(path: PathObject)
    suspend fun deletePath(path: PathObject)
    suspend fun getAllPaths(): List<PathObject>
    suspend fun getAllSkills(): List<SkillObject>
    suspend fun updateSkillStatus(skill: SkillObject)
    suspend fun refreshPaths()
}