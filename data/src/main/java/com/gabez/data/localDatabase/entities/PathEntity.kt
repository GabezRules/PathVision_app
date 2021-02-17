package com.gabez.data.localDatabase.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gabez.pathvisionapp.app.paths.entities.SkillStatus
import com.gabez.pathvisionapp.entities.PathObject
import com.gabez.pathvisionapp.entities.SkillObject

@Entity(tableName = "path")
data class PathEntity(
    @PrimaryKey
    var title: String = "",
    var items: String = ""
){
    fun toPathObject(): PathObject = PathObject(
        title = title,
        items = items.split(";;").map { item -> SkillObject(title = item, status = SkillStatus.EMPTY) }
    )
}