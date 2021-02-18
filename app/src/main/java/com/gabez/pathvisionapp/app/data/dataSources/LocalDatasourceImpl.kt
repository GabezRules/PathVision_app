package com.gabez.pathvisionapp.app.data.dataSources

import com.gabez.pathvisionapp.data.dataSources.LocalDatasource
import com.gabez.pathvisionapp.dataModule.localDatabase.gateway.LocalDbGateway
import com.gabez.pathvisionapp.app.domain.entities.PathObject
import com.gabez.pathvisionapp.app.domain.entities.SkillObject
import kotlinx.coroutines.flow.Flow

class LocalDatasourceImpl(private val gateway: LocalDbGateway): LocalDatasource {

    override suspend fun addPath(path: PathObject){
        gateway.addPath(path)
    }

    override suspend fun deletePath(path: PathObject){
        gateway.deletePath(path)
    }

    override suspend fun updateSkillStatus(skill: SkillObject) {
        gateway.updateSkillStatus(skill)
    }

    override suspend fun getAllPaths(): Flow<List<PathObject>> = gateway.getAllPaths()

    override suspend fun getAllSkills(): Flow<List<SkillObject>> = gateway.getAllSkills()
}