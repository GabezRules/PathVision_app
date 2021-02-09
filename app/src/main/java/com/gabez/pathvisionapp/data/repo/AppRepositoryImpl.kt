package com.gabez.pathvisionapp.data.repo

import com.gabez.pathvisionapp.app.paths.entities.SkillStatus
import com.gabez.pathvisionapp.data.dataSources.ApiDatasource
import com.gabez.pathvisionapp.data.dataSources.FirebaseDatasource
import com.gabez.pathvisionapp.data.dataSources.LocalDatasource
import com.gabez.pathvisionapp.data.localDatabase.entities.PathEntity
import com.gabez.pathvisionapp.data.localDatabase.entities.SkillEntity
import com.gabez.pathvisionapp.data.remoteFirebaseDatabase.PathFirebaseEntity
import com.gabez.pathvisionapp.data.remoteFirebaseDatabase.entities.SkillFirebaseEntity
import com.gabez.pathvisionapp.domain.AppRepository
import kotlinx.coroutines.flow.Flow

class AppRepositoryImpl(
    private val localSource: LocalDatasource,
    private val apiSource: ApiDatasource,
    private val firebaseSource: FirebaseDatasource
) : AppRepository {

    override suspend fun addPath(path: PathEntity) {
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

    override suspend fun deletePath(path: PathEntity){
        localSource.deletePath(path)
        firebaseSource.deletePath(path.name)
    }

    override suspend fun getLocalPaths() = localSource.getAllPaths()
    override suspend fun getLocalSkills(): List<SkillEntity> = localSource.getAllSkills()

    override suspend fun getRemotePaths(): Flow<List<PathFirebaseEntity>> = firebaseSource.getRemotePaths()
    override suspend fun getRemoteSkills(): Flow<List<SkillFirebaseEntity>> = firebaseSource.getRemoteSkills()

    override suspend fun updateSkillStatus(skill: String, newStatus: SkillStatus) {
        localSource.updateSkillStatus(skill, newStatus)

        val nStatus = when (newStatus) {
            SkillStatus.EMPTY -> 0
            SkillStatus.IN_PROGRESS -> 1
            SkillStatus.DONE -> 2
        }

        firebaseSource.updateSkillStatus(SkillFirebaseEntity(name = skill, status = nStatus))
    }
}