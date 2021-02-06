package com.gabez.pathvisionapp.app.search.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gabez.pathvisionapp.R
import com.gabez.pathvisionapp.app.search.entities.PathForSearch
import com.gabez.pathvisionapp.app.search.entities.searchMockData
import com.gabez.pathvisionapp.app.search.view.pathList.ExpandablePathListAdapterSearch
import com.gabez.pathvisionapp.app.search.view.pathList.PathCategoryAdapter
import org.koin.core.KoinComponent
import org.koin.core.inject

class SearchFragment : Fragment(), KoinComponent {

    private lateinit var searchCategoriesList: RecyclerView

    private lateinit var adapterSearch: ExpandablePathListAdapterSearch
    private lateinit var pathList: RecyclerView

    private val viewModel: SearchViewModel by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        searchCategoriesList = view.findViewById(R.id.searchCategoriesList)
        searchCategoriesList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        searchCategoriesList.adapter =
            PathCategoryAdapter(
                arrayListOf(
                    "mobile apps",
                    "backend",
                    "UI/UX design",
                    "project management",
                    "devops",
                    "game development",
                    "other"
                )
            )

        adapterSearch = ExpandablePathListAdapterSearch(searchMockData, this@SearchFragment)

        pathList = view.findViewById(R.id.searchListView)
        pathList.adapter = adapterSearch
        pathList.layoutManager = LinearLayoutManager(requireContext())

        return view
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        adapterSearch.onSaveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        adapterSearch.onRestoreInstanceState(savedInstanceState)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            SearchFragment()
    }

    fun addPath(path: PathForSearch) = viewModel.addPath(path)

    //TODO: Add popup with delete path
    fun deletePath(path: PathForSearch) = viewModel.deletePath(path)
}