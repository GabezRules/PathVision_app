package com.gabez.pathvisionapp.app.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gabez.pathvisionapp.R

class SearchFragment : Fragment() {

    private lateinit var searchCategoriesList: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        searchCategoriesList = view.findViewById(R.id.searchCategoriesList)
        searchCategoriesList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        searchCategoriesList.adapter = PathCategoryAdapter(arrayListOf("mobile apps", "backend", "UI/UX design", "project management", "devops", "game development", "other"))

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = SearchFragment()
    }
}