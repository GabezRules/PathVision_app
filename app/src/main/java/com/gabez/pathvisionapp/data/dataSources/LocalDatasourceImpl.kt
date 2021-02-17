package com.gabez.pathvisionapp.data.dataSources

import com.gabez.pathvisionapp.data.gateways.LocalDbGateway
import com.gabez.pathvisionapp.domain.entities.PathObject
import com.gabez.pathvisionapp.domain.entities.SkillObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class LocalDatasourceImpl(private val gateway: LocalDbGateway): LocalDatasource {

    init {
        GlobalScope.launch(Dispatchers.IO) { refreshPaths() }
    }

    override suspend fun addPath(path: PathObject){
        gateway.addPath(path)
        refreshPaths()
    }

    override suspend fun deletePath(path: PathObject){
        gateway.deletePath(path)
        refreshPaths()
    }

    override suspend fun updateSkillStatus(skill: SkillObject) {
        gateway.updateSkillStatus(skill)
        refreshPaths()
    }

    override suspend fun getAllPaths(): Flow<List<PathObject>> = gateway.getAllPaths()

    override suspend fun getAllSkills(): Flow<List<SkillObject>> = gateway.getAllSkills()

    private suspend fun refreshPaths() = gateway.refreshPaths()
}