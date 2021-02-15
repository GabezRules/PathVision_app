package com.gabez.pathvisionapp.data.dataSources

interface FirebaseDatasource {
    fun addPath(path: PathFirebaseEntity)
    fun deletePath(name: String)

    fun addSkill(skill: SkillFirebaseEntity)
    fun removeSkill(skill: SkillFirebaseEntity)

    fun updateSkillStatus(skill: SkillFirebaseEntity)

    fun getRemotePaths()
    fun getRemoteSkills()
}