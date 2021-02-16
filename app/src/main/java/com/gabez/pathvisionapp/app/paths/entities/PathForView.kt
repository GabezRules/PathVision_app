package com.gabez.pathvisionapp.app.paths.entities
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup


class PathForView(title: String?, items: List<SkillForView?>? = ArrayList(), var category: String = "") : ExpandableGroup<SkillForView?>(title, items)