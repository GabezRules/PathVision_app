package com.gabez.pathvisionapp.data.dataSources

class FirebaseDatasourceImpl(private val gateway: FirebaseAppGateway): FirebaseDatasource {

    override fun addPath(path: PathFirebaseEntity) = gateway.addPath(path)

    override fun deletePath(name: String) = gateway.deletePath(name)

    override fun addSkill(skill: SkillFirebaseEntity) = gateway.addSkill(skill)

    override fun removeSkill(skill: SkillFirebaseEntity) = gateway.addSkill(skill)

    override fun updateSkillStatus(skill: SkillFirebaseEntity) = gateway.updateSkillStatus(skill)

    override fun getRemotePaths() = gateway.getRemotePaths()

    override fun getRemoteSkills() = gateway.getRemoteSkills()
}