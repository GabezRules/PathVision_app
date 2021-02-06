package com.gabez.pathvisionapp.app.paths.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
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
    private lateinit var emptyPathAlert: LinearLayout
    private lateinit var emptyPathAnimation: LottieAnimationView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        pathListView = view.findViewById(R.id.yourPaths)
        emptyPathAlert = view.findViewById(R.id.emptyPathAlert)
        emptyPathAnimation = view.findViewById(R.id.emptyPathAnimation)

        emptyPathAnimation.setAnimation(R.raw.animation_cat)

        adapterMain = ExpandablePathListAdapterMain(ArrayList(), this@MainFragment)

        viewModel.savedPaths.observe(viewLifecycleOwner, Observer{ pathList ->
            if(pathList.isEmpty()){
                pathListView.visibility = View.GONE
                emptyPathAlert.visibility = View.VISIBLE

            }else{
                pathListView.visibility = View.VISIBLE
                emptyPathAlert.visibility = View.GONE

                adapterMain = ExpandablePathListAdapterMain(pathList, this@MainFragment)
            }
        })


        pathListView.adapter = adapterMain
        pathListView.layoutManager = LinearLayoutManager(requireContext())

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