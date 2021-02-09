package com.gabez.pathvisionapp.data.dataSources

import com.gabez.pathvisionapp.data.remoteFirebaseDatabase.dbLogic.FirebaseDbAdapter
import com.gabez.pathvisionapp.data.remoteFirebaseDatabase.entities.PathFirebaseEntity
import com.gabez.pathvisionapp.data.remoteFirebaseDatabase.entities.SkillFirebaseEntity
import kotlinx.coroutines.flow.Flow

class FirebaseDatasourceImpl(private val db: FirebaseDbAdapter): FirebaseDatasource {

    override fun addPath(path: PathFirebaseEntity) = db.addPath(path)

    override fun deletePath(name: String) = db.deletePath(name)

    override fun addSkill(skill: SkillFirebaseEntity) = db.addSkill(skill)

    override fun removeSkill(skill: SkillFirebaseEntity) = db.addSkill(skill)

    override fun updateSkillStatus(skill: SkillFirebaseEntity) = db.updateSkillStatus(skill)

    override fun getRemotePaths(): Flow<List<PathFirebaseEntity>>? = db.getRemotePaths()

    override fun getRemoteSkills(): Flow<List<SkillFirebaseEntity>>? = db.getRemoteSkills()
}