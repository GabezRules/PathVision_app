package com.gabez.pathvisionapp.data.repo

import com.gabez.pathvisionapp.app.paths.entities.SkillStatus
import com.gabez.pathvisionapp.data.dataSources.ApiDatasource
import com.gabez.pathvisionapp.data.dataSources.FirebaseDatasource
import com.gabez.pathvisionapp.data.dataSources.LocalDatasource
import com.gabez.pathvisionapp.app.search.entities.PathForSearch
import com.gabez.pathvisionapp.app.search.entities.SkillForSearch
import com.gabez.pathvisionapp.domain.AppRepository

class AppRepositoryImpl(
    private val localSource: LocalDatasource,
    private val apiSource: ApiDatasource,
    private val firebaseSource: FirebaseDatasource
) : AppRepository {

    override suspend fun addPath(path: PathForSearch) {
        localSource.addPath(path)
        firebaseSource.addPath(
            PathFirebaseEntity(
                name = path.name,
                skills = ArrayList(path.relatedSkills.split(";;").map { skillName ->
                    SkillFirebaseEntity(name = skillName, status = 0)
                })
            )
        )
    }

    override suspend fun deletePath(path: PathForSearch) {
        localSource.deletePath(path)
        firebaseSource.deletePath(path.title)
    }

    override suspend fun getLocalPaths() = localSource.getAllPaths()
    override suspend fun getLocalSkills(): List<SkillForSearch> = localSource.getAllSkills()

    override suspend fun getRemotePaths() = firebaseSource.getRemotePaths()
    override suspend fun getRemoteSkills() = firebaseSource.getRemoteSkills()

    override suspend fun updateSkillStatus(skill: String, newStatus: SkillStatus) {
        localSource.updateSkillStatus(skill, newStatus)

        val nStatus = when (newStatus) {
            SkillStatus.EMPTY -> 0
            SkillStatus.IN_PROGRESS -> 1
            SkillStatus.DONE -> 2
        }

        firebaseSource.updateSkillStatus(SkillFirebaseEntity(name = skill, status = nStatus))
    }

    override suspend fun searchPathByKeyword(keyword: String) = apiSource.searchByKeyword(keyword)
    override suspend fun searchPathBySkill(skill: String) = apiSource.searchBySkill(skill)
}