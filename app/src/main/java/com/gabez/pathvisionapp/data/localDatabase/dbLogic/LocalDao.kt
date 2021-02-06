package com.gabez.pathvisionapp.data.localDatabase.dbLogic

import androidx.room.*
import com.gabez.pathvisionapp.data.localDatabase.entities.PathEntity
import com.gabez.pathvisionapp.data.localDatabase.entities.SkillEntity

@Dao
interface LocalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePath(path: PathEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSkill(skill: SkillEntity)

    @Delete
    suspend fun deletePath(path: PathEntity)

    @Query("DELETE FROM skill WHERE name = :skill")
    suspend fun deleteSkill(skill: String)

    @Query("SELECT * FROM path")
    suspend fun getAllPaths(): List<PathEntity>

    @Query("SELECT * FROM skill")
    suspend fun getAllSkills(): List<SkillEntity>

    @Update
    suspend fun updateSkill(nSkill: SkillEntity)
}