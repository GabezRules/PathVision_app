package com.gabez.data.remoteFirebaseDatabase.gateway

import com.gabez.data.remoteFirebaseDatabase.dbLogic.FirebaseDbAdapter
import com.gabez.data.remoteFirebaseDatabase.entities.PathFirebaseEntity
import com.gabez.data.remoteFirebaseDatabase.entities.SkillFirebaseEntity
import com.gabez.pathvisionapp.app.paths.entities.SkillStatus
import com.gabez.pathvisionapp.data.gateways.FirebaseGateway
import com.gabez.pathvisionapp.entities.PathObject
import com.gabez.pathvisionapp.entities.SkillObject

class FirebaseGatewayImpl(private val db: FirebaseDbAdapter) : FirebaseGateway {
    override fun addPath(path: PathObject) = db.addPath(
        PathFirebaseEntity(
            title = path.title!!,
            items = ArrayList(path.items!!.map { skill ->
                SkillFirebaseEntity(
                    title = skill.title,
                    status = when (skill.status) {
                        SkillStatus.EMPTY -> 0
                        SkillStatus.IN_PROGRESS -> 1
                        SkillStatus.DONE -> 2
                    }
                )
            })
        )
    )

    override fun deletePath(path: PathObject) = db.deletePath(path.title!!)

    override fun addSkill(skill: SkillObject) = db.addSkill(
        SkillFirebaseEntity(
            title = skill.title,
            status = when (skill.status) {
                SkillStatus.EMPTY -> 0
                SkillStatus.IN_PROGRESS -> 1
                SkillStatus.DONE -> 2
            }
        )
    )

    override fun deleteskill(skill: SkillObject) = db.deleteSkill(
        SkillFirebaseEntity(
            title = skill.title,
            status = when (skill.status) {
                SkillStatus.EMPTY -> 0
                SkillStatus.IN_PROGRESS -> 1
                SkillStatus.DONE -> 2
            }
        )
    )

    override fun updateSkillStatus(skill: SkillObject) = db.updateSkillStatus(
        SkillFirebaseEntity(
            title = skill.title,
            status = when (skill.status) {
                SkillStatus.EMPTY -> 0
                SkillStatus.IN_PROGRESS -> 1
                SkillStatus.DONE -> 2
            }
        )
    )

    override fun getRemotePaths() = db.getRemotePaths()

    override fun getRemoteSkills() = db.getRemoteSkills()
}