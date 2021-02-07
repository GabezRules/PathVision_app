package com.gabez.pathvisionapp.app.paths.entities

import com.gabez.pathvisionapp.data.localDatabase.entities.PathEntity
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup


class PathForView(title: String?, items: List<SkillForView?>? = ArrayList(), var category: String = "") : ExpandableGroup<SkillForView?>(title, items){
    fun toPathEntity(): PathEntity {
        return PathEntity(
            name = title,
            category = category,
            relatedSkills = items.joinToString(";;")
        )
    }
}