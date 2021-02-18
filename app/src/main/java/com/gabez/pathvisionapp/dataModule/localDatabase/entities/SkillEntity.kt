package com.gabez.pathvisionapp.dataModule.localDatabase.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gabez.pathvisionapp.app.paths.entities.SkillStatus
import com.gabez.pathvisionapp.domain.entities.SkillObject

@Entity(tableName = "skill")
data class SkillEntity(
    @PrimaryKey
    var title: String = "",
    var status: Int = 0
)