package com.gabez.pathvisionapp.app.paths.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SkillForView(val title: String, var status: SkillStatus): Parcelable