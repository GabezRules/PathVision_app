package com.gabez.pathvisionapp.data.dataSources

import com.gabez.pathvisionapp.app.domain.entities.PathObject
import com.gabez.pathvisionapp.app.domain.entities.SkillObject
import kotlinx.coroutines.flow.Flow

interface FirebaseDatasource {
    fun addPath(path: PathObject)
    fun deletePath(path: PathObject)

    fun addSkill(skill: SkillObject)
    fun removeSkill(skill: SkillObject)

    fun updateSkillStatus(skill: SkillObject)

    fun getRemotePaths(): Flow<PathObject>
    fun getRemoteSkills(): Flow<SkillObject>
}