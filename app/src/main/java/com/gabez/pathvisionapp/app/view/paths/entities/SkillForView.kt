package com.gabez.pathvisionapp.app.view.paths.entities

import android.os.Parcelable
import com.gabez.pathvisionapp.app.domain.entities.SkillObject
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SkillForView(var title: String = "", var status: SkillStatus = SkillStatus.EMPTY): Parcelable{
    fun toSkillObject(): SkillObject = SkillObject(
        title = title,
        status = status
    )
}