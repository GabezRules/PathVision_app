package com.gabez.pathvisionapp.app.search.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gabez.pathvisionapp.R
import com.gabez.pathvisionapp.app.search.entities.PathForSearch
import com.gabez.pathvisionapp.app.search.entities.PathStatus
import com.gabez.pathvisionapp.app.search.entities.searchMockData
import com.gabez.pathvisionapp.app.search.view.pathList.ExpandablePathListAdapterSearch
import com.gabez.pathvisionapp.app.search.view.pathList.PathCategoryAdapter
import com.shreyaspatil.MaterialDialog.BottomSheetMaterialDialog
import org.koin.core.KoinComponent
import org.koin.core.inject

class SearchFragment : Fragment(), KoinComponent {

    private lateinit var searchCategoriesList: RecyclerView

    private lateinit var adapterSearch: ExpandablePathListAdapterSearch
    private lateinit var pathListView: RecyclerView

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

        pathListView = view.findViewById(R.id.searchListView)
        pathListView.adapter = adapterSearch
        pathListView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.searchData.observe(viewLifecycleOwner, Observer{ pathList ->
            adapterSearch = ExpandablePathListAdapterSearch(pathList, this@SearchFragment)
            pathListView.adapter = adapterSearch
            pathListView.invalidate()
        })

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

    fun addPath(path: PathForSearch){
        viewModel.addPath(path)

        val itemPosition = adapterSearch.groups.indexOf(path)
        (adapterSearch.groups[itemPosition] as PathForSearch).status = PathStatus.ADDED
        (pathListView.adapter as ExpandablePathListAdapterSearch).notifyItemChanged(itemPosition)
    }

    fun deletePath(path: PathForSearch){
        val mBottomSheetDialog = BottomSheetMaterialDialog.Builder(requireActivity())
            .setTitle("Delete?")
            .setMessage("You will loose your skill progress. Are you sure you want to delete this path? ")
            .setCancelable(false)
            .setPositiveButton("Delete", R.drawable.ic_delete) { dialogInterface, _ ->
                viewModel.deletePath(path)

                val itemPosition = adapterSearch.groups.indexOf(path)
                (adapterSearch.groups[itemPosition] as PathForSearch).status = PathStatus.NOT_ADDED
                (pathListView.adapter as ExpandablePathListAdapterSearch).notifyItemChanged(itemPosition)

                dialogInterface.dismiss()
            }
            .setNegativeButton("Cancel") { dialogInterface, _ -> dialogInterface.dismiss() }
            .build()



        mBottomSheetDialog.show()
    }
}