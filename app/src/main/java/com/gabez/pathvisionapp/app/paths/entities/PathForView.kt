package com.gabez.pathvisionapp.app.paths.entities
import com.gabez.pathvisionapp.entities.PathObject
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup


class PathForView(override var title: String?, override var items: List<SkillForView?>? = ArrayList()) : ExpandableGroup<SkillForView?>(title, items), PathObject