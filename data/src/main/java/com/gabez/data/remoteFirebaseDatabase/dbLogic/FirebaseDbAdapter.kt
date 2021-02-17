package com.gabez.data.remoteFirebaseDatabase.dbLogic

import com.gabez.data.remoteFirebaseDatabase.entities.PathFirebaseEntity
import com.gabez.data.remoteFirebaseDatabase.entities.SkillFirebaseEntity
import com.gabez.pathvisionapp.domain.entities.PathObject
import com.gabez.pathvisionapp.domain.entities.SkillObject
import kotlinx.coroutines.flow.Flow

interface FirebaseDbAdapter {
    fun addPath(path: PathFirebaseEntity)
    fun deletePath(name: String)

    fun addSkill(skill: SkillFirebaseEntity)
    fun deleteSkill(skill: SkillFirebaseEntity)

    fun updateSkillStatus(skill: SkillFirebaseEntity)

    fun getRemotePaths(): Flow<PathObject>
    fun getRemoteSkills(): Flow<SkillObject>
}