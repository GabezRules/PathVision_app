package com.gabez.pathvisionapp.data.dataSources

import com.gabez.pathvisionapp.app.paths.entities.PathForView
import com.gabez.pathvisionapp.app.paths.entities.SkillForView
import com.gabez.pathvisionapp.data.gateways.FirebaseGateway

class FirebaseDatasourceImpl(private val gateway: FirebaseGateway): FirebaseDatasource {

    override fun addPath(path: PathForView) = gateway.addPath(path)

    override fun deletePath(name: String) = gateway.deletePath(name)

    override fun addSkill(skill: SkillForView) = gateway.addSkill(skill)

    override fun removeSkill(skill: SkillForView) = gateway.addSkill(skill)

    override fun updateSkillStatus(skill: SkillForView) = gateway.updateSkillStatus(skill)

    override fun getRemotePaths() = gateway.getRemotePaths()

    override fun getRemoteSkills() = gateway.getRemoteSkills()
}