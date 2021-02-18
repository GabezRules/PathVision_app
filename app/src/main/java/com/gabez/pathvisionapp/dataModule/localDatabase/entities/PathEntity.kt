package com.gabez.pathvisionapp.dataModule.localDatabase.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "path")
data class PathEntity(
    @PrimaryKey
    var title: String = "",
    var items: String = ""
)