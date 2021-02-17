package com.gabez.data.localDatabase.gateway

import com.gabez.data.localDatabase.dbLogic.LocalDatabase
import com.gabez.data.localDatabase.entities.PathEntity
import com.gabez.data.localDatabase.entities.SkillEntity
import com.gabez.pathvisionapp.app.paths.entities.SkillStatus
import com.gabez.pathvisionapp.app.statusHolders.DbSkillCountHolder
import com.gabez.pathvisionapp.data.gateways.LocalDbGateway
import com.gabez.pathvisionapp.domain.entities.PathObject
import com.gabez.pathvisionapp.domain.entities.SkillObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalDbGatewayImpl(private val db: LocalDatabase): LocalDbGateway {
    //TODO: Items from MainFragment are not deleting - fix it

    var skillHolder = DbSkillCountHolder()

    override suspend fun addPath(path: PathObject) {
        val entity = PathEntity(
            title = path.title!!,
            items = path.items!!.joinToString(";;")
        )

        for(skill in entity.items.split(";;")){
            if(!skillHolder.isInDb(skill)) db.dao().saveSkill(
                SkillEntity(
                    title = skill,
                    status = 0
                )
            )
        }

        db.dao().savePath(entity)
    }

    override suspend fun deletePath(path: PathObject) {
        val entity = PathEntity(
            title = path.title!!,
            items = path.items!!.joinToString(";;")
        )

        db.dao().deletePath(entity)

        for(skill in entity.items.split(";;")){
            //TODO: Potential bug here
            //if(!skillHolder.isInOtherPath(skill)) db.dao().deleteSkill(skill)
        }
    }

    override suspend fun getAllPaths(): Flow<List<PathObject>> =
        db.dao().getAllPaths().map { pathList -> pathList.map { pathEntity -> pathEntity.toPathObject() } }


    override suspend fun getAllSkills(): Flow<List<SkillObject>> =
        db.dao().getAllSkills().map{skillList -> skillList.map { skillEntity -> skillEntity.toSkillObject() }}


    override suspend fun updateSkillStatus(skill: SkillObject) {
        val intStatus = when(skill.status){
            SkillStatus.EMPTY -> 0
            SkillStatus.IN_PROGRESS -> 1
            SkillStatus.DONE -> 2
        }

        db.dao().updateSkill(skill.title, intStatus)
    }

}