package com.gabez.data.remoteApiDatabase.entities

import com.gabez.pathvisionapp.app.search.entities.PathForSearch
import com.gabez.pathvisionapp.app.search.entities.PathStatus
import com.gabez.pathvisionapp.app.search.entities.SkillForSearch

data class PathFromServer(var job_name: String = "", var required_skills: List<String> = ArrayList(), var q: Float = 0F){
    fun toPathForSearch(): PathForSearch = PathForSearch(title = job_name, items = required_skills.map { item -> SkillForSearch(title = item) }, status = PathStatus.NOT_ADDED)
}