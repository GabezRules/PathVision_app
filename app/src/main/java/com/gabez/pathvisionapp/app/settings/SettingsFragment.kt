package com.gabez.pathvisionapp.app.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gabez.pathvisionapp.R
import com.google.android.material.button.MaterialButton
import com.greenfrvr.hashtagview.HashtagView


class SettingsFragment : Fragment() {

    private lateinit var observedTagsContainer: HashtagView
    private lateinit var buttonLogin: MaterialButton
    private lateinit var buttonRegister: MaterialButton

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        observedTagsContainer = view.findViewById(R.id.settingsObservedTags)
        observedTagsContainer.setData(arrayListOf("mobile apps", "backend", "UI/UX design", "project management", "devops", "game development", "other"))

        buttonLogin = view.findViewById(R.id.buttonLogin)
        buttonLogin.setOnClickListener { showLoginDialog() }

        buttonRegister = view.findViewById(R.id.buttonRegister)
        buttonRegister.setOnClickListener { showRegisterDialog() }

        return view
    }

    fun showLoginDialog(){

    }

    fun showRegisterDialog(){

    }

    companion object {
        @JvmStatic
        fun newInstance() =
            SettingsFragment()
    }
}