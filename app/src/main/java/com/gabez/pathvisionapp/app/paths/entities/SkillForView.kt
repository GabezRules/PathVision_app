package com.gabez.pathvisionapp.app.paths.entities

import android.os.Parcelable
import com.gabez.data.localDatabase.entities.SkillEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SkillForView(val title: String, var status: SkillStatus): Parcelable{
    fun toSkillEntity(): SkillEntity{
        return SkillEntity(
            name = title,
            status = when(status){
                SkillStatus.EMPTY -> 0
                SkillStatus.IN_PROGRESS -> 1
                SkillStatus.DONE -> 2
            }
        )
    }
}