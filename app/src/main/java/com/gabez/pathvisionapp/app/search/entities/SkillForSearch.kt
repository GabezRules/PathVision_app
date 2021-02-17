package com.gabez.pathvisionapp.app.search.entities

import android.os.Parcelable
import com.gabez.pathvisionapp.app.paths.entities.SkillStatus
import com.gabez.pathvisionapp.domain.entities.SkillObject
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SkillForSearch(val title: String): Parcelable{
    fun toSkillObject(): SkillObject = SkillObject(
        title = title,
        status = SkillStatus.EMPTY
    )
}