package com.gabez.pathvisionapp.app.settings.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gabez.pathvisionapp.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.button.MaterialButton

class RegisterBottomsheetFragment: BottomSheetDialogFragment() {

    private lateinit var cancelBtn: MaterialButton

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_bottomsheet_register, container, false)

        cancelBtn = view.findViewById(R.id.buttonCancel)
        cancelBtn.setOnClickListener { this@RegisterBottomsheetFragment.dismiss() }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(): RegisterBottomsheetFragment = RegisterBottomsheetFragment()
    }
}