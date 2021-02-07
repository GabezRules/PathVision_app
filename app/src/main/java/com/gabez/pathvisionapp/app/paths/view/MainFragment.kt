package com.gabez.pathvisionapp.app.paths.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.gabez.pathvisionapp.R
import com.gabez.pathvisionapp.app.paths.entities.PathForView
import com.gabez.pathvisionapp.app.paths.entities.SkillForView
import com.gabez.pathvisionapp.app.paths.entities.SkillStatus
import com.gabez.pathvisionapp.app.paths.view.pathList.ExpandablePathListAdapterMain
import com.shreyaspatil.MaterialDialog.BottomSheetMaterialDialog
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

        viewModel.savedPaths.observeForever{ pathList ->
            if(pathList.isEmpty()) showEmptyPathAlert()
            else{
                hidePathAlert()
                adapterMain = ExpandablePathListAdapterMain(pathList, this@MainFragment)
                pathListView.adapter = adapterMain
            }
        }

        viewModel.isLoading.observeForever{  isLoading ->
            if(isLoading) showLoading()
            else hidePathAlert()
        }

        pathListView.adapter = adapterMain
        pathListView.layoutManager = LinearLayoutManager(requireContext())

        return view
    }

    fun changeSkillStatus(skill: SkillForView, newStatus: SkillStatus) = viewModel.updateSkillStatus(skill, newStatus)

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //adapterMain.onSaveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        //adapterMain.onRestoreInstanceState(savedInstanceState)
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

    fun deletePath(path: PathForView){
        val mBottomSheetDialog = BottomSheetMaterialDialog.Builder(requireActivity())
            .setTitle("Delete?")
            .setMessage("You will loose your skill progress. Are you sure you want to delete this path? ")
            .setCancelable(false)
            .setPositiveButton("Delete", R.drawable.ic_delete) { dialogInterface, _ ->
                viewModel.deletePath(path)

                val itemPosition = adapterMain.groups.indexOf(path)
                (pathListView.adapter as ExpandablePathListAdapterMain).groups.removeAt(itemPosition)
                (pathListView.adapter as ExpandablePathListAdapterMain).groups.remove(path)
                (pathListView.adapter as ExpandablePathListAdapterMain).notifyItemRemoved(itemPosition)


                pathListView.invalidate()
                pathListView.invalidateItemDecorations()

                dialogInterface.dismiss()
            }
            .setNegativeButton("Cancel") { dialogInterface, _ -> dialogInterface.dismiss() }
            .build()

        mBottomSheetDialog.show()
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}