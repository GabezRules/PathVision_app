package com.gabez.data.localDatabase.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gabez.pathvisionapp.app.paths.entities.SkillStatus
import com.gabez.pathvisionapp.entities.SkillObject

@Entity(tableName = "skill")
data class SkillEntity(
    @PrimaryKey
    var title: String = "",
    var status: Int = 0
){
    fun toSkillObject(): SkillObject = SkillObject(
        title = title,
        status = when(status){
            0 -> SkillStatus.EMPTY
            1 -> SkillStatus.IN_PROGRESS
            2 -> SkillStatus.DONE
            else -> SkillStatus.EMPTY
        }
    )
}