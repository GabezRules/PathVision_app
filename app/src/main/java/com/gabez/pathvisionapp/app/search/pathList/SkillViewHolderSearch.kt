package com.gabez.pathvisionapp.app.search.pathList

import android.content.Context
import android.view.View
import android.widget.TextView
import com.gabez.pathvisionapp.R
import com.gabez.pathvisionapp.app.search.entities.SkillForSearch
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder

class SkillViewHolderSearch(private var itemViewSkill: View, var context: Context) : ChildViewHolder(itemViewSkill) {
    private val skillName: TextView = itemViewSkill.findViewById(R.id.skillName)

    fun setupSkill(skill: SkillForSearch) {
        skillName.text = skill.title
    }
}