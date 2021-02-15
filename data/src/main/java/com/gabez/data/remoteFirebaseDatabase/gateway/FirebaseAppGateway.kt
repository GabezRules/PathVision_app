package com.gabez.data.remoteFirebaseDatabase.gateway

import com.gabez.data.remoteFirebaseDatabase.entities.PathFirebaseEntity
import com.gabez.data.remoteFirebaseDatabase.entities.SkillFirebaseEntity

interface FirebaseAppGateway {
    fun addPath(path: PathFirebaseEntity)
    fun deletePath(name: String)
    fun addSkill(skill: SkillFirebaseEntity)
    fun removeSkill(skill: SkillFirebaseEntity)
    fun updateSkillStatus(skill: SkillFirebaseEntity)
    fun getRemotePaths()
    fun getRemoteSkills()
}