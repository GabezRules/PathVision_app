package com.gabez.pathvisionapp.app.view.paths.entities
import com.gabez.pathvisionapp.app.domain.entities.PathObject
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup


class PathForView(title: String?, items: List<SkillForView?>? = ArrayList()) : ExpandableGroup<SkillForView?>(title, items){
    fun toPathObject(): PathObject = PathObject(
        title = title,
        items = items.map{skillForView -> skillForView!!.toSkillObject()}
    )
}