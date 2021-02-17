package com.gabez.pathvisionapp.entities

import com.gabez.pathvisionapp.app.search.entities.PathStatus

data class PathObject(open var title: String? = "", open var items: List<SkillObject>?, open var status: PathStatus = PathStatus.NOT_ADDED)
