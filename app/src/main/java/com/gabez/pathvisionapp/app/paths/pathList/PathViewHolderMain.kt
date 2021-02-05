package com.gabez.pathvisionapp.app.paths.pathList

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieAnimationView
import com.gabez.pathvisionapp.R
import com.gabez.pathvisionapp.app.CircularCompletionView
import com.gabez.pathvisionapp.app.paths.MainFragment
import com.gabez.pathvisionapp.app.paths.entities.SkillForView
import com.gabez.pathvisionapp.app.paths.entities.SkillStatus
import com.shreyaspatil.MaterialDialog.BottomSheetMaterialDialog
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder


class PathViewHolderMain(itemView: View, private val callback: MainFragment) : GroupViewHolder(itemView) {
    private val jobTitle: TextView = itemView.findViewById(R.id.pathJobName)
    private val amountSkills: TextView = itemView.findViewById(R.id.pathAmountSkills)
    private val completionPercent: CircularCompletionView = itemView.findViewById(R.id.pathCompletionPercent)
    private val expandBtn: ImageView = itemView.findViewById(R.id.pathExpand)
    val deleteBtn: ImageView = itemView.findViewById(R.id.pathDelete)

    fun setupPathViewHolder(group: ExpandableGroup<*>) {
        jobTitle.text = group.title
        amountSkills.text = "${group.itemCount} skills"

        completionPercent.setTextSize(32F)
        completionPercent.setStrokeSize(15F)
        completionPercent.setCompletionPercentage(getCompletionPercent(group))
        completionPercent.invalidate()

        deleteBtn.setOnClickListener { showDeleteDialog() }
    }

    private fun getCompletionPercent(group: ExpandableGroup<*>): Int{
        var skillsDone: Int = 0
        for(item in group.items) if((item as SkillForView).status == SkillStatus.DONE) skillsDone += 1
        return (100*skillsDone/group.items.size)
    }

    override fun expand() {
        super.expand()
        expandBtn.animate().rotation(180F).start()
    }

    fun showDeleteDialog(){
        //TODO: Scale trash can animation
        val mBottomSheetDialog = BottomSheetMaterialDialog.Builder((callback as Fragment).requireActivity())
            .setTitle("Delete?")
            .setMessage("You will loose your skill progress. Are you sure you want to delete this path? ")
            .setCancelable(false)
            .setPositiveButton("Delete", R.drawable.ic_delete) { dialogInterface, _ ->
                //TODO: delete item
                Toast.makeText(callback.requireContext(), "Item deleted!", Toast.LENGTH_SHORT).show()
                dialogInterface.dismiss()
            }
            .setNegativeButton("Cancel") { dialogInterface, _ -> dialogInterface.dismiss() }
            .build()



        mBottomSheetDialog.show()
    }
}