package com.gabez.pathvisionapp.app.paths.entities

import android.os.Parcel
import android.os.Parcelable
import com.gabez.pathvisionapp.entities.SkillObject
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SkillForView(var title: String = "", var status: SkillStatus = SkillStatus.EMPTY): Parcelable{
    fun toSkillObject(): SkillObject = SkillObject(
        title = title,
        status = status
    )
}