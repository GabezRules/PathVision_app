package com.gabez.pathvisionapp.app.search.entities

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup

class PathForSearch(title: String?, var status: PathStatus, items: List<SkillForSearch?>?, var category: String = "") : ExpandableGroup<SkillForSearch?>(title, items)