package com.gabez.pathvisionapp.data.repo

import com.gabez.pathvisionapp.app.paths.entities.SkillStatus
import com.gabez.pathvisionapp.data.dataSources.ApiDatasource
import com.gabez.pathvisionapp.data.dataSources.LocalDatasource
import com.gabez.pathvisionapp.data.localDatabase.entities.PathEntity
import com.gabez.pathvisionapp.data.localDatabase.entities.SkillEntity
import com.gabez.pathvisionapp.domain.AppRepository

class AppRepositoryImpl(private val localSource: LocalDatasource, private val apiSource: ApiDatasource): AppRepository {

    override suspend fun addPath(path: PathEntity) = localSource.addPath(path)

    override suspend fun deletePath(path: PathEntity) = localSource.deletePath(path)

    override suspend fun getLocalPaths() = localSource.getAllPaths()
    override suspend fun getLocalSkills(): List<SkillEntity> = localSource.getAllSkills()

    override suspend fun updateSkillStatus(skill: String, newStatus: SkillStatus) = localSource.updateSkillStatus(skill, newStatus)
}