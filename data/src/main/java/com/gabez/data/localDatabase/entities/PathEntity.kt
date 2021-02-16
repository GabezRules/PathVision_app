package com.gabez.data.localDatabase.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "path")
data class PathEntity(
    @PrimaryKey
    var name: String = "",
    var category: String = "",
    var relatedSkills: String = ""
)