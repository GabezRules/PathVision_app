package com.gabez.pathvisionapp.app.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gabez.pathvisionapp.R
import com.gabez.pathvisionapp.app.settings.authentication.LoginBottomsheetFragment
import com.gabez.pathvisionapp.app.settings.authentication.RegisterBottomsheetFragment
import com.google.android.material.button.MaterialButton
import com.greenfrvr.hashtagview.HashtagView


class SettingsFragment : Fragment() {

    private lateinit var buttonLogin: MaterialButton
    private lateinit var buttonRegister: MaterialButton

    val LOGIN_FRAGMENT_TAG = "com.gabez.pathvisionapp.app.settings.LOGIN_FRAGMENT"
    val REGISTER_FRAGMENT_TAG = "com.gabez.pathvisionapp.app.settings.REGISTER_FRAGMENT"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        buttonLogin = view.findViewById(R.id.buttonLogin)
        buttonLogin.setOnClickListener { showLoginDialog() }

        buttonRegister = view.findViewById(R.id.buttonRegister)
        buttonRegister.setOnClickListener { showRegisterDialog() }

        return view
    }

    fun showLoginDialog() {
        requireActivity().supportFragmentManager.let {
            LoginBottomsheetFragment.newInstance().apply { show(it, LOGIN_FRAGMENT_TAG) }
        }
    }

    fun showRegisterDialog() {
        requireActivity().supportFragmentManager.let {
            RegisterBottomsheetFragment.newInstance().apply { show(it, REGISTER_FRAGMENT_TAG) }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            SettingsFragment()
    }
}