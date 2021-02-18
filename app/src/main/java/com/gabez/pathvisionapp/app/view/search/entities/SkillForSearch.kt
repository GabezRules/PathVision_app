package com.gabez.pathvisionapp.app.view.search.entities

import android.os.Parcelable
import com.gabez.pathvisionapp.app.view.paths.entities.SkillStatus
import com.gabez.pathvisionapp.app.domain.entities.SkillObject
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SkillForSearch(val title: String): Parcelable{
    fun toSkillObject(): SkillObject = SkillObject(
        title = title,
        status = SkillStatus.EMPTY
    )
}