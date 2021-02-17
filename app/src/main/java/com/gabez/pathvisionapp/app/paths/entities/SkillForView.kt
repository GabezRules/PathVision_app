package com.gabez.pathvisionapp.app.paths.entities

import android.os.Parcel
import android.os.Parcelable
import com.gabez.pathvisionapp.entities.SkillObject
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SkillForView(override var title: String = "", override var status: SkillStatus = SkillStatus.EMPTY): Parcelable, SkillObject