package com.gabez.pathvisionapp.dataModule.localDatabase.dbLogic

import androidx.room.*
import com.gabez.pathvisionapp.dataModule.localDatabase.entities.PathEntity
import com.gabez.pathvisionapp.dataModule.localDatabase.entities.SkillEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePath(path: PathEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSkill(skill: SkillEntity)

    @Delete
    suspend fun deletePath(path: PathEntity)

    @Query("DELETE FROM skill WHERE title = :skill")
    suspend fun deleteSkill(skill: String)

    @Query("SELECT * FROM path")
    suspend fun getAllPaths(): List<PathEntity>

    @Query("SELECT * FROM skill")
    suspend fun getAllSkills(): List<SkillEntity>

    @Query("UPDATE skill SET status=:newStatus WHERE title = :skillName")
    suspend fun updateSkill(skillName: String, newStatus: Int)
}