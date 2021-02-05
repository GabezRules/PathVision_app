package com.gabez.pathvisionapp.app.paths.pathList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gabez.pathvisionapp.R
import com.gabez.pathvisionapp.app.paths.entities.PathForView
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup


class ExpandablePathListAdapter(groups: List<ExpandableGroup<*>?>?, private val context: Context) :
    ExpandableRecyclerViewAdapter<PathViewHolder, SkillViewHolder>(groups) {

    override fun onCreateGroupViewHolder(parent: ViewGroup, viewType: Int): PathViewHolder {

        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_path, parent, false)
        return PathViewHolder(view)
    }

    override fun onCreateChildViewHolder(parent: ViewGroup, viewType: Int): SkillViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_skill, parent, false)
        return SkillViewHolder(view, context)
    }

    override fun onBindChildViewHolder(holder: SkillViewHolder, flatPosition: Int, group: ExpandableGroup<*>, childIndex: Int)
            = holder.setupSkill((group as PathForView).items[childIndex]!!)


    override fun onBindGroupViewHolder(holder: PathViewHolder, flatPosition: Int, group: ExpandableGroup<*>)
            = holder.setupPathViewHolder(group)

}