package com.gabez.pathvisionapp.data.localDatabase.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "skill")
data class SkillEntity(
    @PrimaryKey
    var name: String = "",
    var status: Int = 0
)