package com.gabez.pathvisionapp.data.dataSources

import com.gabez.pathvisionapp.app.paths.entities.SkillStatus
import com.gabez.pathvisionapp.data.localDatabase.DbPathsHolder
import com.gabez.pathvisionapp.data.localDatabase.DbSkillCountHolder
import com.gabez.pathvisionapp.data.localDatabase.dbLogic.LocalDatabase
import com.gabez.pathvisionapp.data.localDatabase.entities.PathEntity
import com.gabez.pathvisionapp.data.localDatabase.entities.SkillEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LocalDatasourceImpl(private val db: LocalDatabase, private val pathsHolder: DbPathsHolder): LocalDatasource {

    //TODO: Items from MainFragment are not deleting - fix it

    var skillHolder = DbSkillCountHolder()

    init {
        GlobalScope.launch(Dispatchers.IO) { refreshPaths() }
    }

    override suspend fun addPath(path: PathEntity){
        db.dao().savePath(path)

        for(skill in path.relatedSkills.split(";;")){
            if(!skillHolder.isInDb(skill)) db.dao().saveSkill(
                SkillEntity(
                    name = skill,
                    status = 0
                )
            )
        }

        refreshPaths()
    }

    override suspend fun deletePath(path: PathEntity){
        db.dao().deletePath(path)

        for(skill in path.relatedSkills.split(";;")){
            //TODO: Potential bug here
            //if(!skillHolder.isInOtherPath(skill)) db.dao().deleteSkill(skill)
        }

        refreshPaths()
    }

    override suspend fun updateSkillStatus(skill: String, newStatus: SkillStatus) {
        val intStatus = when(newStatus){
            SkillStatus.EMPTY -> 0
            SkillStatus.IN_PROGRESS -> 1
            SkillStatus.DONE -> 2
        }

        db.dao().updateSkill(skill, intStatus)

        refreshPaths()
    }

    override suspend fun getAllPaths(): List<PathEntity> {
        val allPaths = db.dao().getAllPaths()
        skillHolder.refreshSkillHolder(allPaths)

        return allPaths
    }

    override suspend fun getAllSkills(): List<SkillEntity> = db.dao().getAllSkills()

    private suspend fun refreshPaths(){
        val allPaths = db.dao().getAllPaths()
        val allSkills = db.dao().getAllSkills()

        skillHolder.refreshSkillHolder(allPaths)
        pathsHolder.setPaths(allPaths, allSkills)
    }
}