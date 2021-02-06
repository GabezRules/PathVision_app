package com.gabez.pathvisionapp.app.search.view.pathList

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.gabez.pathvisionapp.R
import com.gabez.pathvisionapp.app.search.entities.PathForSearch
import com.gabez.pathvisionapp.app.search.entities.PathStatus
import com.gabez.pathvisionapp.app.search.view.SearchFragment
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder


class PathViewHolderSearch(itemView: View, val callback: SearchFragment) : GroupViewHolder(itemView) {
    private val jobTitle: TextView = itemView.findViewById(R.id.pathJobName)
    private val amountSkills: TextView = itemView.findViewById(R.id.pathAmountSkills)
    private val isAdded: ImageView = itemView.findViewById(R.id.pathAddBtn)
    val expandBtn: ImageView = itemView.findViewById(R.id.pathExpand)

    fun setupPathViewHolder(group: ExpandableGroup<*>) {
        jobTitle.text = group.title
        amountSkills.text = "${group.itemCount} skills"

        when((group as PathForSearch).status){
            PathStatus.ADDED -> {
                setIconSaved()
                isAdded.setOnClickListener {
                    callback.deletePath(group)
                    setIconEmpty()
                }
            }

            PathStatus.NOT_ADDED -> {
                setIconEmpty()
                isAdded.setOnClickListener {
                    callback.addPath(group)
                    setIconSaved()
                }
            }
        }
    }

    override fun expand() {
        super.expand()
        expandBtn.animate().rotation(180F).start()
    }

    fun setIconEmpty(){
        isAdded.setImageResource(R.drawable.ic_add_empty)
        isAdded.setColorFilter(ContextCompat.getColor(callback.requireContext(), R.color.colorGray), android.graphics.PorterDuff.Mode.SRC_IN)
    }

    fun setIconSaved(){
        isAdded.setImageResource(R.drawable.ic_add_full)
        isAdded.setColorFilter(ContextCompat.getColor(callback.requireContext(), R.color.colorPrimary), android.graphics.PorterDuff.Mode.SRC_IN)
    }
}