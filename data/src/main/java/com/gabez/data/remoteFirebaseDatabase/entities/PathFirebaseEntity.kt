package com.gabez.data.remoteFirebaseDatabase.entities

import com.gabez.pathvisionapp.entities.PathObject

class PathFirebaseEntity(var title: String = "", var items: ArrayList<SkillFirebaseEntity> = ArrayList()){
    fun toPathObject(): PathObject = PathObject(
        title = title,
        items = items.map{ skill -> skill.toSkillObject()}
    )
}