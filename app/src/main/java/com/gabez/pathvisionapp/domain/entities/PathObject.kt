package com.gabez.pathvisionapp.domain.entities

import com.gabez.pathvisionapp.app.search.entities.PathStatus

data class PathObject(var title: String? = "", var items: List<SkillObject>?, var status: PathStatus = PathStatus.NOT_ADDED)
