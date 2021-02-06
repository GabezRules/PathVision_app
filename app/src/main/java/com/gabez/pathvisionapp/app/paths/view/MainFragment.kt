package com.gabez.pathvisionapp.app.paths.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gabez.pathvisionapp.R
import com.gabez.pathvisionapp.app.paths.entities.mainMockData
import com.gabez.pathvisionapp.app.paths.view.pathList.ExpandablePathListAdapterMain
import org.koin.core.KoinComponent
import org.koin.core.inject

class MainFragment : Fragment(), KoinComponent {

    private val viewModel: MainViewModel by inject()
    private lateinit var adapterMain: ExpandablePathListAdapterMain
    private lateinit var pathList: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)


        adapterMain = ExpandablePathListAdapterMain(mainMockData, this@MainFragment)

        pathList = view.findViewById(R.id.yourPaths)
        pathList.adapter = adapterMain
        pathList.layoutManager = LinearLayoutManager(requireContext())

        return view
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        adapterMain.onSaveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        adapterMain.onRestoreInstanceState(savedInstanceState)
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}