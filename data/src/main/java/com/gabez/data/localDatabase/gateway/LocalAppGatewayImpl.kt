package com.gabez.data.localDatabase.gateway

import com.gabez.data.localDatabase.dbLogic.LocalDatabase
import com.gabez.data.localDatabase.entities.SkillEntity
import com.gabez.pathvisionapp.app.paths.entities.SkillStatus
import com.gabez.pathvisionapp.app.search.entities.PathForSearch
import com.gabez.pathvisionapp.app.search.entities.PathStatus
import com.gabez.pathvisionapp.app.search.entities.SkillForSearch
import com.gabez.pathvisionapp.data.dataHolders.DbPathsHolder
import com.gabez.pathvisionapp.data.dataHolders.DbSkillCountHolder
import com.gabez.pathvisionapp.data.gateways.LocalDbGateway

class LocalAppGatewayImpl(private val db: LocalDatabase, private val pathsHolder: DbPathsHolder): LocalDbGateway {
    //TODO: Items from MainFragment are not deleting - fix it

    var skillHolder = DbSkillCountHolder()

    override suspend fun addPath(path: PathForSearch) {
        val entity = path.toPathEntity()
        for(skill in entity.relatedSkills.split(";;")){
            if(!skillHolder.isInDb(skill)) db.dao().saveSkill(
                SkillEntity(
                    name = skill,
                    status = 0
                )
            )
        }
    }

    override suspend fun deletePath(path: PathForSearch) {
        val entity = path.toPathEntity()
        db.dao().deletePath(entity)

        for(skill in entity.relatedSkills.split(";;")){
            //TODO: Potential bug here
            //if(!skillHolder.isInOtherPath(skill)) db.dao().deleteSkill(skill)
        }
    }

    override suspend fun getAllPaths(): List<PathForSearch> {
        val allPaths = db.dao().getAllPaths()
        skillHolder.refreshSkillHolder(allPaths)

        return allPaths.map { entity -> PathForSearch(
            title = entity.name,
            items = entity.relatedSkills.split(";;").map { skill -> SkillForSearch(title = skill)},
            status = PathStatus.NOT_ADDED
        ) }
    }

    override suspend fun getAllSkills(): List<SkillForSearch> {
        return db.dao().getAllSkills().map { skillEntity -> SkillForSearch(title = skillEntity.name) }
    }

    override suspend fun updateSkillStatus(skill: String, newStatus: SkillStatus) {
        val intStatus = when(newStatus){
            SkillStatus.EMPTY -> 0
            SkillStatus.IN_PROGRESS -> 1
            SkillStatus.DONE -> 2
        }

        db.dao().updateSkill(skill, intStatus)
    }

    override suspend fun refreshPaths(){
        val allPaths = db.dao().getAllPaths()
        val allSkills = db.dao().getAllSkills()

        skillHolder.refreshSkillHolder(allPaths)
        pathsHolder.setPaths(allPaths, allSkills)
    }

}