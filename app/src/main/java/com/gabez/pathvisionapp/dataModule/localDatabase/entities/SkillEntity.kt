package com.gabez.pathvisionapp.dataModule.localDatabase.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "skill")
data class SkillEntity(
    @PrimaryKey
    var title: String = "",
    var status: Int = 0
)