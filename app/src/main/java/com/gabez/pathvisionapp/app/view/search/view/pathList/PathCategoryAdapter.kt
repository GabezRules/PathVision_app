package com.gabez.pathvisionapp.app.view.search.view.pathList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gabez.pathvisionapp.R

class PathCategoryAdapter(val list: List<String>): RecyclerView.Adapter<PathCategoryAdapter.PathCategoryViewHolder>() {
    class PathCategoryViewHolder(view: View): RecyclerView.ViewHolder(view){
        var itemCategory: TextView = view.findViewById(R.id.itemCategory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PathCategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return PathCategoryViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: PathCategoryViewHolder, position: Int) {
        holder.itemCategory.text = list[position]
    }

    override fun getItemCount(): Int = list.size
}