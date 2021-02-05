package com.gabez.pathvisionapp.app.search.entities

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup

class PathForSearch(title: String?, var status: PathStatus, items: List<SkillForSearch?>?) : ExpandableGroup<SkillForSearch?>(title, items)