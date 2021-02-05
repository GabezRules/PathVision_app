package com.gabez.pathvisionapp.app.paths.entities

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup


class PathForView(title: String?, items: List<SkillForView?>?) : ExpandableGroup<SkillForView?>(title, items)