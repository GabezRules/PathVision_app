package com.gabez.pathvisionapp.data.dataSources

import com.gabez.pathvisionapp.dataModule.remoteFirebaseDatabase.gateway.FirebaseGateway
import com.gabez.pathvisionapp.app.domain.entities.PathObject
import com.gabez.pathvisionapp.app.domain.entities.SkillObject
import kotlinx.coroutines.flow.Flow

class FirebaseDatasourceImpl(private val gateway: FirebaseGateway): FirebaseDatasource {

    override fun addPath(path: PathObject) = gateway.addPath(path)

    override fun deletePath(path: PathObject) = gateway.deletePath(path)

    override fun addSkill(skill: SkillObject) = gateway.addSkill(skill)

    override fun removeSkill(skill: SkillObject) = gateway.addSkill(skill)

    override fun updateSkillStatus(skill: SkillObject) = gateway.updateSkillStatus(skill)

    override fun getRemotePaths(): Flow<PathObject> = gateway.getRemotePaths()

    override fun getRemoteSkills(): Flow<SkillObject> = gateway.getRemoteSkills()
}