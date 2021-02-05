package com.gabez.pathvisionapp.app.paths.pathList

import android.view.View
import android.widget.TextView
import com.gabez.pathvisionapp.R
import com.gabez.pathvisionapp.app.CircularCompletionView
import com.gabez.pathvisionapp.app.paths.entities.SkillForView
import com.gabez.pathvisionapp.app.paths.entities.SkillStatus
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder


class PathViewHolder(itemView: View) : GroupViewHolder(itemView) {
    private val jobTitle: TextView = itemView.findViewById(R.id.pathJobName)
    private val amountSkills: TextView = itemView.findViewById(R.id.pathAmountSkills)
    private val completionPercent: CircularCompletionView = itemView.findViewById(R.id.pathCompletionPercent)

    fun setupPathViewHolder(group: ExpandableGroup<*>) {
        jobTitle.text = group.title
        amountSkills.text = "${group.itemCount} skills"

        completionPercent.setTextSize(16F)
        completionPercent.setStrokeSize(20F)
        completionPercent.setCompletionPercentage(getCompletionPercent(group))

    }

    private fun getCompletionPercent(group: ExpandableGroup<*>): Int{
        var skillsDone: Int = 0
        for(item in group.items) if((item as SkillForView).status == SkillStatus.DONE) skillsDone += 1
        return 100*(skillsDone/group.items.size)
    }
}