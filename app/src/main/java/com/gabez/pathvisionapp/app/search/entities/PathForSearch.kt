package com.gabez.pathvisionapp.app.search.entities

import com.gabez.data.localDatabase.entities.PathEntity
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup

class PathForSearch(title: String?, var status: PathStatus, items: List<SkillForSearch?>?, var category: String = "") : ExpandableGroup<SkillForSearch?>(title, items){
    fun toPathEntity(): PathEntity {
        return PathEntity(
            name = title,
            category = category,
            relatedSkills = items.joinToString(";;")
        )
    }
}