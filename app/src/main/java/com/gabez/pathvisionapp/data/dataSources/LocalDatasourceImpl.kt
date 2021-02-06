package com.gabez.pathvisionapp.data.dataSources

import com.gabez.pathvisionapp.data.localDatabase.DbSkillHolder
import com.gabez.pathvisionapp.data.localDatabase.dbLogic.LocalDatabase
import com.gabez.pathvisionapp.data.localDatabase.entities.PathEntity
import com.gabez.pathvisionapp.data.localDatabase.entities.SkillEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

class LocalDatasourceImpl(private val db: LocalDatabase): LocalDatasource {

    var skillHolder = DbSkillHolder()

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

        skillHolder.refreshSkillHolder(db.dao().getAllPaths())
    }

    override suspend fun deletePath(path: PathEntity){
        db.dao().deletePath(path)
        for(skill in path.relatedSkills.split(";;")){
            if(!skillHolder.isInOtherPath(skill)) db.dao().deleteSkill(skill)
        }

        skillHolder.refreshSkillHolder(db.dao().getAllPaths())
    }

    override suspend fun getAllPaths(): List<PathEntity> {
        skillHolder.refreshSkillHolder(db.dao().getAllPaths())

        return db.dao().getAllPaths()
    }

    override suspend fun getAllSkills(): List<SkillEntity> = db.dao().getAllSkills()
}