package com.gabez.pathvisionapp.app.view.search.entities

import com.gabez.pathvisionapp.app.domain.entities.PathObject
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup

class PathForSearch(title: String?, var status: PathStatus, items: List<SkillForSearch?>?) : ExpandableGroup<SkillForSearch?>(title, items){
    fun toPathObject(): PathObject = PathObject(
        title = title,
        items = items.map{skillForSearch -> skillForSearch!!.toSkillObject()}
    )
}