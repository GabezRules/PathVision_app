package com.gabez.pathvisionapp.data.remoteFirebaseDatabase.dbLogic

import com.gabez.pathvisionapp.data.remoteFirebaseDatabase.entities.PathFirebaseEntity
import com.gabez.pathvisionapp.data.remoteFirebaseDatabase.entities.SkillFirebaseEntity
import kotlinx.coroutines.flow.Flow

interface FirebaseDbAdapter {
    fun addPath(path: PathFirebaseEntity)
    fun deletePath(name: String)

    fun addSkill(skill: SkillFirebaseEntity)
    fun removeSkill(skill: SkillFirebaseEntity)

    fun updateSkillStatus(skill: SkillFirebaseEntity)

    fun getRemotePaths(): Flow<List<PathFirebaseEntity>>
    fun getRemoteSkills(): Flow<List<SkillFirebaseEntity>>
}