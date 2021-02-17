package com.gabez.data.localDatabase.gateway

import com.gabez.data.localDatabase.dbLogic.LocalDatabase
import com.gabez.data.localDatabase.entities.PathEntity
import com.gabez.data.localDatabase.entities.SkillEntity
import com.gabez.pathvisionapp.app.paths.entities.SkillStatus
import com.gabez.pathvisionapp.app.search.entities.PathForSearch
import com.gabez.pathvisionapp.app.search.entities.PathStatus
import com.gabez.pathvisionapp.app.search.entities.SkillForSearch
import com.gabez.pathvisionapp.data.dataHolders.DbPathsHolder
import com.gabez.pathvisionapp.data.dataHolders.DbSkillCountHolder
import com.gabez.pathvisionapp.data.gateways.LocalDbGateway
import com.gabez.pathvisionapp.entities.PathObject
import com.gabez.pathvisionapp.entities.SkillObject

class LocalDbGatewayImpl(private val db: LocalDatabase, private val pathsHolder: DbPathsHolder): LocalDbGateway {
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

    override suspend fun getAllPaths(): List<PathObject> {
        val allPaths = db.dao().getAllPaths()
        skillHolder.refreshSkillHolder(allPaths.map { path -> path.toPathObject() })

        return allPaths.map { entity -> PathObject(
            title = entity.title,
            items = entity.items.split(";;").map { skill -> SkillObject(title = skill) },
            status = PathStatus.ADDED
        ) }
    }

    override suspend fun getAllSkills(): List<SkillObject> {
        return db.dao().getAllSkills().map { skillEntity -> SkillObject(title = skillEntity.title) }
    }

    override suspend fun updateSkillStatus(skill: SkillObject) {
        val intStatus = when(skill.status){
            SkillStatus.EMPTY -> 0
            SkillStatus.IN_PROGRESS -> 1
            SkillStatus.DONE -> 2
        }

        db.dao().updateSkill(skill.title, intStatus)
    }

    override suspend fun refreshPaths(){
        val allPaths = db.dao().getAllPaths()
        val allSkills = db.dao().getAllSkills()

        skillHolder.refreshSkillHolder(allPaths.map { path -> path.toPathObject() })
        pathsHolder.setPaths(allPaths.map { path -> path.toPathObject() }, allSkills.map { skill -> skill.toSkillObject() })
    }

}