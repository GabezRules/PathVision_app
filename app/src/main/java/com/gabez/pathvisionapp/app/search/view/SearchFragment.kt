package com.gabez.pathvisionapp.app.search.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gabez.pathvisionapp.R
import com.gabez.pathvisionapp.app.search.entities.PathForSearch
import com.gabez.pathvisionapp.app.search.entities.PathStatus
import com.gabez.pathvisionapp.app.search.entities.SearchType
import com.gabez.pathvisionapp.app.search.view.pathList.ExpandablePathListAdapterSearch
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.shreyaspatil.MaterialDialog.BottomSheetMaterialDialog
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.core.KoinComponent
import org.koin.core.inject

class SearchFragment : Fragment(), KoinComponent {

    private lateinit var adapterSearch: ExpandablePathListAdapterSearch
    private lateinit var pathListView: RecyclerView
    private lateinit var buttonSearch: MaterialButton

    private lateinit var searchEditText: TextInputEditText
    private lateinit var errorMessage: TextView

    private val viewModel: SearchViewModel by inject()

    private lateinit var searchByKeyword: RadioButton
    private lateinit var searchBySkill: RadioButton

    @InternalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        adapterSearch = ExpandablePathListAdapterSearch(ArrayList(), this@SearchFragment)
        searchEditText = view.findViewById(R.id.searchPath)

        buttonSearch = view.findViewById(R.id.buttonSearch)
        buttonSearch.setOnClickListener { viewModel.searchPath(searchEditText.text.toString()) }

        pathListView = view.findViewById(R.id.searchListView)
        pathListView.adapter = adapterSearch
        pathListView.layoutManager = LinearLayoutManager(requireContext())

        searchByKeyword = view.findViewById(R.id.byKeyword)
        searchBySkill = view.findViewById(R.id.bySkills)

        viewModel.searchData.observe(viewLifecycleOwner, Observer { pathList ->
            adapterSearch = ExpandablePathListAdapterSearch(pathList, this@SearchFragment)
            pathListView.adapter = adapterSearch
            pathListView.invalidate()
        })

        errorMessage = view.findViewById(R.id.errorMessage)

        viewModel.error.observeForever { error ->
            if (error != "") {
                pathListView.visibility = View.GONE
                errorMessage.visibility = View.VISIBLE
                errorMessage.text = error
            } else {
                pathListView.visibility = View.VISIBLE
                errorMessage.visibility = View.GONE
            }
        }

        viewModel.isLoading.observeForever { isLoading ->
            if (isLoading) {
                pathListView.visibility = View.GONE
                errorMessage.visibility = View.VISIBLE
                errorMessage.text = "loading"
            } else {
                pathListView.visibility = View.VISIBLE
                errorMessage.visibility = View.GONE
            }

        }

        viewModel.searchType.observeForever { searchType ->
            when(searchType){
                SearchType.BY_KEYWORD -> searchEditText.setHint(R.string.search_by_keyword)
                SearchType.BY_SKILL -> searchEditText.setHint(R.string.search_by_skill)
                else -> searchEditText.setHint(R.string.search_by_keyword)
            }
        }

        searchByKeyword.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) viewModel.searchType.value = SearchType.BY_KEYWORD
        }

        searchBySkill.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) viewModel.searchType.value = SearchType.BY_SKILL
        }

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

    fun addPath(path: PathForSearch) {
        viewModel.addPath(path)

        val itemPosition = adapterSearch.groups.indexOf(path)
        (adapterSearch.groups[itemPosition] as PathForSearch).status = PathStatus.ADDED
        (pathListView.adapter as ExpandablePathListAdapterSearch).notifyItemChanged(itemPosition)
    }

    fun deletePath(path: PathForSearch) {
        val mBottomSheetDialog = BottomSheetMaterialDialog.Builder(requireActivity())
            .setTitle("Delete?")
            .setMessage("You will loose your skill progress. Are you sure you want to delete this path? ")
            .setCancelable(false)
            .setPositiveButton("Delete", R.drawable.ic_delete) { dialogInterface, _ ->
                viewModel.deletePath(path)

                val itemPosition = adapterSearch.groups.indexOf(path)
                (adapterSearch.groups[itemPosition] as PathForSearch).status = PathStatus.NOT_ADDED
                (pathListView.adapter as ExpandablePathListAdapterSearch).notifyItemChanged(
                    itemPosition
                )

                dialogInterface.dismiss()
            }
            .setNegativeButton("Cancel") { dialogInterface, _ -> dialogInterface.dismiss() }
            .build()

        mBottomSheetDialog.show()
    }
}