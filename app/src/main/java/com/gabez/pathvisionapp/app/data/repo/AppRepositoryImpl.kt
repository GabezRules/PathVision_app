package com.gabez.pathvisionapp.data.repo

import com.gabez.pathvisionapp.data.dataSources.ApiDatasource
import com.gabez.pathvisionapp.data.dataSources.FirebaseDatasource
import com.gabez.pathvisionapp.data.dataSources.LocalDatasource
import com.gabez.pathvisionapp.app.domain.AppRepository
import com.gabez.pathvisionapp.app.domain.entities.PathObject
import com.gabez.pathvisionapp.app.domain.entities.SkillObject
import kotlinx.coroutines.flow.Flow

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

    override suspend fun getLocalPaths(): Flow<List<PathObject>> = localSource.getAllPaths()
    override suspend fun getLocalSkills(): Flow<List<SkillObject>> = localSource.getAllSkills()

    override suspend fun getRemotePaths(): Flow<PathObject> = firebaseSource.getRemotePaths()
    override suspend fun getRemoteSkills(): Flow<SkillObject> = firebaseSource.getRemoteSkills()

    override suspend fun updateSkillStatus(skill: SkillObject) {
        localSource.updateSkillStatus(skill)
        firebaseSource.updateSkillStatus(skill)
    }

    override suspend fun searchPathByKeyword(keyword: String): Flow<List<PathObject>>  = apiSource.searchByKeyword(keyword)
    override suspend fun searchPathBySkill(skill: String): Flow<List<PathObject>>  = apiSource.searchBySkill(skill)
}