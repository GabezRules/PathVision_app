package com.gabez.pathvisionapp.dataModule.remoteFirebaseDatabase.entities

import com.gabez.pathvisionapp.app.domain.entities.PathObject

class PathFirebaseEntity(var title: String = "", var items: ArrayList<SkillFirebaseEntity> = ArrayList()){
    fun toPathObject(): PathObject = PathObject(
        title = title,
        items = items.map{ skill -> skill.toSkillObject()}
    )
}