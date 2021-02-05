package com.gabez.pathvisionapp.data.localDatabase.dbLogic

import androidx.room.*
import com.gabez.pathvisionapp.data.localDatabase.entities.PathEntity
import com.gabez.pathvisionapp.data.localDatabase.entities.SkillEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePath(path: PathEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSkill(skill: SkillEntity)

    @Delete
    suspend fun deletePath(path: PathEntity)

    @Delete
    suspend fun deleteSkill(skill: SkillEntity)

    @Query("SELECT * FROM path")
    suspend fun getAllPaths(): Flow<List<PathEntity>>

    @Query("SELECT * FROM skill")
    suspend fun getAllSkills(): Flow<List<SkillEntity>>

    @Update
    suspend fun updateSkill(nSkill: SkillEntity)
}