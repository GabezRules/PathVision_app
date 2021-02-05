package com.gabez.pathvisionapp.app.search.pathList

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.gabez.pathvisionapp.R
import com.gabez.pathvisionapp.app.search.entities.PathForSearch
import com.gabez.pathvisionapp.app.search.entities.PathStatus
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder


class PathViewHolderSearch(itemView: View, val context: Context) : GroupViewHolder(itemView) {
    private val jobTitle: TextView = itemView.findViewById(R.id.pathJobName)
    private val amountSkills: TextView = itemView.findViewById(R.id.pathAmountSkills)
    private val isAdded: ImageView = itemView.findViewById(R.id.pathAddBtn)
    val expandBtn: ImageView = itemView.findViewById(R.id.pathExpand)

    fun setupPathViewHolder(group: ExpandableGroup<*>) {
        jobTitle.text = group.title
        amountSkills.text = "${group.itemCount} skills"

        when((group as PathForSearch).status){
            PathStatus.ADDED -> {
                isAdded.setImageResource(R.drawable.ic_add_full)
                isAdded.setColorFilter(ContextCompat.getColor(context, R.color.colorPrimary), android.graphics.PorterDuff.Mode.SRC_IN)
            }

            PathStatus.NOT_ADDED -> {
                isAdded.setImageResource(R.drawable.ic_add_empty)
                isAdded.setColorFilter(ContextCompat.getColor(context, R.color.colorGray), android.graphics.PorterDuff.Mode.SRC_IN)
            }
        }
    }

    override fun expand() {
        super.expand()
        expandBtn.animate().rotation(180F).start()
    }
}