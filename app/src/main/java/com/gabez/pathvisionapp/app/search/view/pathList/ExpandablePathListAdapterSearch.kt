package com.gabez.pathvisionapp.app.search.view.pathList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gabez.pathvisionapp.R
import com.gabez.pathvisionapp.app.search.entities.PathForSearch
import com.gabez.pathvisionapp.app.search.view.SearchFragment
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup

class ExpandablePathListAdapterSearch(groups: List<ExpandableGroup<*>?>?, private val callback: SearchFragment) :
    ExpandableRecyclerViewAdapter<PathViewHolderSearch, SkillViewHolderSearch>(groups) {

    override fun onCreateGroupViewHolder(parent: ViewGroup, viewType: Int): PathViewHolderSearch {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_path_insearch, parent, false)
        return PathViewHolderSearch(view, callback)
    }

    override fun onCreateChildViewHolder(parent: ViewGroup, viewType: Int): SkillViewHolderSearch {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_skill_insearch, parent, false)
        return SkillViewHolderSearch(view, callback.requireContext())
    }

    override fun onBindChildViewHolder(holderSearch: SkillViewHolderSearch, flatPosition: Int, group: ExpandableGroup<*>, childIndex: Int)
            = holderSearch.setupSkill((group as PathForSearch).items[childIndex]!!)


    override fun onBindGroupViewHolder(holder: PathViewHolderSearch, flatPosition: Int, group: ExpandableGroup<*>)
            = holder.setupPathViewHolder(group)

}