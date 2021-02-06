package com.gabez.pathvisionapp.app.paths.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.gabez.pathvisionapp.R
import com.gabez.pathvisionapp.app.paths.view.pathList.ExpandablePathListAdapterMain
import org.koin.core.KoinComponent
import org.koin.core.inject

class MainFragment : Fragment(), KoinComponent {

    private val viewModel: MainViewModel by inject()
    private lateinit var adapterMain: ExpandablePathListAdapterMain
    private lateinit var pathListView: RecyclerView

    private lateinit var pathAlertContainer: LinearLayout
    private lateinit var pathAlertAnimation: LottieAnimationView
    private lateinit var pathAlertTitle: TextView
    private lateinit var pathAlertDesc: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        pathListView = view.findViewById(R.id.yourPaths)

        pathAlertContainer = view.findViewById(R.id.pathAlert)
        pathAlertAnimation = view.findViewById(R.id.pathAlertAnimation)
        pathAlertTitle = view.findViewById(R.id.pathAlertTitle)
        pathAlertDesc = view.findViewById(R.id.pathAlertDesc)

        pathAlertAnimation.setAnimation(R.raw.animation_cat)

        adapterMain = ExpandablePathListAdapterMain(ArrayList(), this@MainFragment)

        viewModel.savedPaths.observe(viewLifecycleOwner, Observer{ pathList ->
            if(pathList.isEmpty()) showEmptyPathAlert()
            else{
                hidePathAlert()
                adapterMain = ExpandablePathListAdapterMain(pathList, this@MainFragment)
                pathListView.adapter = adapterMain
                //Toast.makeText(requireContext(), pathList.size.toString(), Toast.LENGTH_LONG).show()
            }
        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer{ isLoading ->
            if(isLoading) showLoading()
            else hidePathAlert()
        })

        pathListView.adapter = adapterMain
        pathListView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.getAllPaths()

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

    private fun showEmptyPathAlert(){
        pathAlertTitle.setText(R.string.no_paths_added)
        pathAlertDesc.setText(R.string.no_paths_added_desc)

        pathListView.visibility = View.GONE
        pathAlertContainer.visibility = View.VISIBLE
    }

    private fun hidePathAlert(){
        pathListView.visibility = View.VISIBLE
        pathAlertContainer.visibility = View.GONE
    }

    private fun showLoading(){
        pathAlertTitle.setText(R.string.loading)
        pathAlertDesc.setText(R.string.empty_string)

        pathListView.visibility = View.GONE
        pathAlertContainer.visibility = View.VISIBLE
    }
}