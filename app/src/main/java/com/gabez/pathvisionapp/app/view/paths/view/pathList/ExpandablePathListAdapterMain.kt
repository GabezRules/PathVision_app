package com.gabez.pathvisionapp.app.view.paths.view.pathList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gabez.pathvisionapp.R
import com.gabez.pathvisionapp.app.view.paths.view.MainFragment
import com.gabez.pathvisionapp.app.view.paths.entities.PathForView
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup


class ExpandablePathListAdapterMain(groups: List<ExpandableGroup<*>?>?, private val callback: MainFragment) :
    ExpandableRecyclerViewAdapter<PathViewHolderMain, SkillViewHolderMain>(groups) {

    override fun onCreateGroupViewHolder(parent: ViewGroup, viewType: Int): PathViewHolderMain {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_path, parent, false)
        return PathViewHolderMain(view, callback)
    }

    override fun onCreateChildViewHolder(parent: ViewGroup, viewType: Int): SkillViewHolderMain {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_skill, parent, false)
        return SkillViewHolderMain(view, callback)
    }

    override fun onBindChildViewHolder(holderMain: SkillViewHolderMain, flatPosition: Int, group: ExpandableGroup<*>, childIndex: Int)
            = holderMain.setupSkill((group as PathForView).items[childIndex]!!)


    override fun onBindGroupViewHolder(holder: PathViewHolderMain, flatPosition: Int, group: ExpandableGroup<*>)
            = holder.setupPathViewHolder(group)

}