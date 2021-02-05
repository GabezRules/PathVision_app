package com.gabez.pathvisionapp.domain.entities

data class PathObj(
    var name: String = "",
    var category: String = "",
    var relatedSkills: List<SkillObj> = ArrayList()
)