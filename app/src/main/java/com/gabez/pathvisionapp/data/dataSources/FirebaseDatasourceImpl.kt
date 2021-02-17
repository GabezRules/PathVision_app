package com.gabez.pathvisionapp.data.dataSources

import com.gabez.pathvisionapp.app.paths.entities.PathForView
import com.gabez.pathvisionapp.app.paths.entities.SkillForView
import com.gabez.pathvisionapp.data.gateways.FirebaseGateway
import com.gabez.pathvisionapp.entities.PathObject
import com.gabez.pathvisionapp.entities.SkillObject

class FirebaseDatasourceImpl(private val gateway: FirebaseGateway): FirebaseDatasource {

    override fun addPath(path: PathObject) = gateway.addPath(path)

    override fun deletePath(path: PathObject) = gateway.deletePath(path)

    override fun addSkill(skill: SkillObject) = gateway.addSkill(skill)

    override fun removeSkill(skill: SkillObject) = gateway.addSkill(skill)

    override fun updateSkillStatus(skill: SkillObject) = gateway.updateSkillStatus(skill)

    override fun getRemotePaths() = gateway.getRemotePaths()

    override fun getRemoteSkills() = gateway.getRemoteSkills()
}