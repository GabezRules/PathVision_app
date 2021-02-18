package com.gabez.pathvisionapp.dataModule.remoteApiDatabase.entities

import com.gabez.pathvisionapp.app.view.search.entities.PathStatus
import com.gabez.pathvisionapp.app.domain.entities.PathObject
import com.gabez.pathvisionapp.app.domain.entities.SkillObject

data class PathFromServer(var job_name: String = "", var required_skills: List<String> = ArrayList(), var q: Float = 0F){
    fun toPathObject(): PathObject = PathObject(title = job_name, items = required_skills.map { item -> SkillObject(title = item) }, status = PathStatus.NOT_ADDED)
}