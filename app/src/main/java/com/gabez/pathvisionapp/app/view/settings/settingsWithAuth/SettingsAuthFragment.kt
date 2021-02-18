package com.gabez.pathvisionapp.app.view.settings.settingsWithAuth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.gabez.pathvisionapp.R
import com.gabez.pathvisionapp.app.view.settings.settingsWithoutAuth.SettingsViewModel
import com.google.android.material.button.MaterialButton
import org.koin.core.KoinComponent
import org.koin.core.inject

class SettingsAuthFragment : Fragment(), KoinComponent {

    private lateinit var buttonLogout: MaterialButton
    private lateinit var buttonDeleteAccount: MaterialButton
    private lateinit var username: TextView

    private val viewModel: SettingsViewModel by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_settings_auth, container, false)

        buttonLogout = view.findViewById(R.id.buttonLogout)
        buttonLogout.setOnClickListener { viewModel.logout() }

        buttonDeleteAccount = view.findViewById(R.id.buttonDeleteAccount)
        buttonDeleteAccount.setOnClickListener { viewModel.deleteAccount() }

        username = view.findViewById(R.id.username)

        viewModel.currentUser.observeForever { currentUser -> if(currentUser!=null) username.text = currentUser.login }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = SettingsAuthFragment()
    }
}