package com.gabez.pathvisionapp.data.repo

import com.gabez.pathvisionapp.app.paths.entities.SkillStatus
import com.gabez.pathvisionapp.data.dataSources.ApiDatasource
import com.gabez.pathvisionapp.data.dataSources.FirebaseDatasource
import com.gabez.pathvisionapp.data.dataSources.LocalDatasource
import com.gabez.pathvisionapp.domain.AppRepository
import com.gabez.pathvisionapp.entities.PathObject
import com.gabez.pathvisionapp.entities.SkillObject

class AppRepositoryImpl(
    private val localSource: LocalDatasource,
    private val apiSource: ApiDatasource,
    private val firebaseSource: FirebaseDatasource
) : AppRepository {

    override suspend fun addPath(path: PathObject) {
        localSource.addPath(path)
        firebaseSource.addPath(path)
    }

    override suspend fun deletePath(path: PathObject) {
        localSource.deletePath(path)
        firebaseSource.deletePath(path)
    }

    override suspend fun getLocalPaths() = localSource.getAllPaths()
    override suspend fun getLocalSkills(): List<SkillObject> = localSource.getAllSkills()

    override suspend fun getRemotePaths() = firebaseSource.getRemotePaths()
    override suspend fun getRemoteSkills() = firebaseSource.getRemoteSkills()

    override suspend fun updateSkillStatus(skill: SkillObject) {
        localSource.updateSkillStatus(skill)
        firebaseSource.updateSkillStatus(skill)
    }

    override suspend fun searchPathByKeyword(keyword: String) = apiSource.searchByKeyword(keyword)
    override suspend fun searchPathBySkill(skill: String) = apiSource.searchBySkill(skill)
}