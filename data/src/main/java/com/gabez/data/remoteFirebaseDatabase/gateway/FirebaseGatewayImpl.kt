package com.gabez.data.remoteFirebaseDatabase.gateway

import com.gabez.data.remoteFirebaseDatabase.dbLogic.FirebaseDbAdapter
import com.gabez.data.remoteFirebaseDatabase.entities.PathFirebaseEntity
import com.gabez.data.remoteFirebaseDatabase.entities.SkillFirebaseEntity
import com.gabez.pathvisionapp.app.paths.entities.PathForView
import com.gabez.pathvisionapp.app.paths.entities.SkillForView
import com.gabez.pathvisionapp.app.paths.entities.SkillStatus
import com.gabez.pathvisionapp.data.gateways.FirebaseGateway

class FirebaseGatewayImpl(private val db: FirebaseDbAdapter): FirebaseGateway {
    override fun addPath(path: PathForView) = db.addPath(
        PathFirebaseEntity(
        title = path.title,
        items = ArrayList(path.items.map { skill -> SkillFirebaseEntity(
            title = skill!!.title,
            status = when(skill.status){
                SkillStatus.EMPTY -> 0
                SkillStatus.IN_PROGRESS -> 1
                SkillStatus.DONE -> 2
            }
        ) })
    ))

    override fun deletePath(name: String) = db.deletePath(name)

    override fun addSkill(skill: SkillForView) = db.addSkill(skill.toSkillEntity())

    override fun removeSkill(skill: SkillForView) = db.addSkill(skill.toSkillEntity())

    override fun updateSkillStatus(skill: SkillForView) = db.updateSkillStatus(skill.toSkillEntity())

    override fun getRemotePaths() = db.getRemotePaths()

    override fun getRemoteSkills() = db.getRemoteSkills()
}