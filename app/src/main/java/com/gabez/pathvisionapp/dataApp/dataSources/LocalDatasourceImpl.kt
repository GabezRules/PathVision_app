package com.gabez.pathvisionapp.dataApp.dataSources

import com.gabez.pathvisionapp.data.dataSources.LocalDatasource
import com.gabez.pathvisionapp.dataModule.localDatabase.gateway.LocalDbGateway
import com.gabez.pathvisionapp.domain.entities.PathObject
import com.gabez.pathvisionapp.domain.entities.SkillObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

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