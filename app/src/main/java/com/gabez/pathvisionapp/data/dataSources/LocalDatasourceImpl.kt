package com.gabez.pathvisionapp.data.dataSources

import com.gabez.pathvisionapp.app.paths.entities.SkillStatus
import com.gabez.pathvisionapp.app.search.entities.PathForSearch
import com.gabez.pathvisionapp.app.search.entities.SkillForSearch
import com.gabez.pathvisionapp.data.gateways.LocalDbGateway
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LocalDatasourceImpl(private val gateway: LocalDbGateway): LocalDatasource {

    init {
        GlobalScope.launch(Dispatchers.IO) { refreshPaths() }
    }

    override suspend fun addPath(path: PathForSearch){
        gateway.addPath(path)
        refreshPaths()
    }

    override suspend fun deletePath(path: PathForSearch){
        gateway.deletePath(path)
        refreshPaths()
    }

    override suspend fun updateSkillStatus(skill: String, newStatus: SkillStatus) {
        gateway.updateSkillStatus(skill, newStatus)
        refreshPaths()
    }

    override suspend fun getAllPaths(): List<PathForSearch> = gateway.getAllPaths()

    override suspend fun getAllSkills(): List<SkillForSearch> = gateway.getAllSkills()

    private suspend fun refreshPaths() = gateway.refreshPaths()
}