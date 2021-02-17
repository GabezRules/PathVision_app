package com.gabez.data.remoteFirebaseDatabase.entities

import com.gabez.pathvisionapp.app.paths.entities.SkillStatus
import com.gabez.pathvisionapp.domain.entities.SkillObject

data class SkillFirebaseEntity(var title: String = "", var status: Int = 0){
    fun toSkillObject(): SkillObject = SkillObject(
        title = title,
        status = when(status){
            0 -> SkillStatus.EMPTY
            1 -> SkillStatus.IN_PROGRESS
            2 -> SkillStatus.DONE
            else -> SkillStatus.EMPTY
        }
    )
}