package com.gabez.pathvisionapp.dataModule.localDatabase.gateway

import com.gabez.pathvisionapp.dataModule.localDatabase.dbLogic.LocalDatabase
import com.gabez.pathvisionapp.dataModule.localDatabase.entities.PathEntity
import com.gabez.pathvisionapp.dataModule.localDatabase.entities.SkillEntity
import com.gabez.pathvisionapp.app.view.paths.entities.SkillStatus
import com.gabez.pathvisionapp.app.view.statusHolders.DbSkillCountHolder
import com.gabez.pathvisionapp.app.domain.entities.PathObject
import com.gabez.pathvisionapp.app.domain.entities.SkillObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LocalDbGatewayImpl(private val db: LocalDatabase) : LocalDbGateway {
    //TODO: Items from MainFragment are not deleting - fix it

    var skillHolder = DbSkillCountHolder()

    override suspend fun addPath(path: PathObject) {
        val entity = PathEntity(
            title = path.title!!,
            items = path.items!!.joinToString(";;")
        )

        for (skill in entity.items.split(";;")) {
            if (!skillHolder.isInDb(skill)) db.dao().saveSkill(
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

        for (skill in entity.items.split(";;")) {
            //TODO: Potential bug here
            //if(!skillHolder.isInOtherPath(skill)) db.dao().deleteSkill(skill)
        }
    }

    override suspend fun getAllPaths(): Flow<List<PathObject>> = flow {
        val pathsList = db.dao().getAllPaths()
        val properList = pathsList.map { pathEntity ->
            PathObject(
                title = pathEntity.title,
                items = pathEntity.items.split(";;")
                    .map { item -> SkillObject(title = item, status = SkillStatus.EMPTY) }
            )
        }

        emit(value = properList)
    }


    override suspend fun getAllSkills(): Flow<List<SkillObject>> = flow {
        val skillsList = db.dao().getAllSkills()
        val properList = skillsList.map { skillEntity ->
            SkillObject(
                title = skillEntity.title,
                status = when (skillEntity.status) {
                    0 -> SkillStatus.EMPTY
                    1 -> SkillStatus.IN_PROGRESS
                    2 -> SkillStatus.DONE
                    else -> SkillStatus.EMPTY
                }
            )
        }

        emit(value = properList)
    }


    override suspend fun updateSkillStatus(skill: SkillObject) {
        val intStatus = when (skill.status) {
            SkillStatus.EMPTY -> 0
            SkillStatus.IN_PROGRESS -> 1
            SkillStatus.DONE -> 2
        }

        db.dao().updateSkill(skill.title, intStatus)
    }

}