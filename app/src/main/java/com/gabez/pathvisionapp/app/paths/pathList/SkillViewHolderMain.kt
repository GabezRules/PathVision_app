package com.gabez.pathvisionapp.app.paths.pathList

import android.content.Context
import android.view.View
import android.widget.ImageView

import android.widget.TextView
import androidx.core.content.ContextCompat
import com.gabez.pathvisionapp.R
import com.gabez.pathvisionapp.app.paths.entities.SkillForView
import com.gabez.pathvisionapp.app.paths.entities.SkillStatus

import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder


class SkillViewHolderMain(private var itemViewSkill: View, var context: Context) : ChildViewHolder(itemViewSkill) {
    private val skillName: TextView = itemViewSkill.findViewById(R.id.skillName)
    private val emptyBtn: ImageView = itemViewSkill.findViewById(R.id.skillPause)
    private val inProgressBtn: ImageView = itemViewSkill.findViewById(R.id.skillInProgress)
    private val doneBtn: ImageView = itemViewSkill.findViewById(R.id.skillDone)

    fun setupSkill(skill: SkillForView) {
        skillName.text = skill.title

        when(skill.status){
            SkillStatus.EMPTY -> {
                emptyBtn.setImageResource(R.drawable.ic_pause_full)
                emptyBtn.setColorFilter(ContextCompat.getColor(context, R.color.colorPrimary), android.graphics.PorterDuff.Mode.SRC_IN)
                inProgressBtn.setColorFilter(ContextCompat.getColor(context, R.color.colorGray), android.graphics.PorterDuff.Mode.SRC_IN)
                doneBtn.setColorFilter(ContextCompat.getColor(context, R.color.colorGray), android.graphics.PorterDuff.Mode.SRC_IN)
                doneBtn.setImageResource(R.drawable.ic_done_empty)
            }
            SkillStatus.IN_PROGRESS -> {
                emptyBtn.setImageResource(R.drawable.ic_pause_empty)
                emptyBtn.setColorFilter(ContextCompat.getColor(context, R.color.colorGray), android.graphics.PorterDuff.Mode.SRC_IN)
                inProgressBtn.setColorFilter(ContextCompat.getColor(context, R.color.colorPrimaryDark), android.graphics.PorterDuff.Mode.SRC_IN)
                doneBtn.setColorFilter(ContextCompat.getColor(context, R.color.colorGray), android.graphics.PorterDuff.Mode.SRC_IN)
                doneBtn.setImageResource(R.drawable.ic_done_empty)
            }
            SkillStatus.DONE -> {
                emptyBtn.setImageResource(R.drawable.ic_pause_empty)
                emptyBtn.setColorFilter(ContextCompat.getColor(context, R.color.colorGray), android.graphics.PorterDuff.Mode.SRC_IN)
                inProgressBtn.setColorFilter(ContextCompat.getColor(context, R.color.colorGray), android.graphics.PorterDuff.Mode.SRC_IN)
                doneBtn.setColorFilter(ContextCompat.getColor(context, R.color.colorPrimaryLight), android.graphics.PorterDuff.Mode.SRC_IN)
                doneBtn.setImageResource(R.drawable.ic_done_full)
            }
        }

        //TODO: Set on button click listener
    }

}