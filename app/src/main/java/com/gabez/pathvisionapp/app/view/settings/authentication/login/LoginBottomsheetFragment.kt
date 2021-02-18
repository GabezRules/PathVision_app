package com.gabez.pathvisionapp.app.view.settings.authentication.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import com.gabez.pathvisionapp.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import org.koin.core.KoinComponent
import org.koin.core.inject

class LoginBottomsheetFragment: BottomSheetDialogFragment(), KoinComponent {

    //TODO: Make this UI a bit prettier please

    private lateinit var loginEmail: TextInputEditText
    private lateinit var loginPassword: TextInputEditText

    private lateinit var loginBtn: MaterialButton
    private lateinit var cancelBtn: MaterialButton

    private lateinit var loginContainer: ConstraintLayout
    private lateinit var loginLoading: ProgressBar

    private val viewModel: LoginViewModel by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_bottomsheet_login, container, false)

        loginEmail = view.findViewById(R.id.loginEmail)
        loginPassword = view.findViewById(R.id.loginPassword)

        cancelBtn = view.findViewById(R.id.buttonCancel)
        cancelBtn.setOnClickListener { this@LoginBottomsheetFragment.dismiss() }

        loginBtn = view.findViewById(R.id.buttonLogin)
        loginBtn.setOnClickListener { viewModel.loginUser(loginEmail.text.toString(), loginPassword.text.toString()) }

        loginContainer = view.findViewById(R.id.loginContainer)
        loginLoading = view.findViewById(R.id.loginLoading)

        viewModel.authenticationError.observeForever { error -> loginEmail.error = error }
        viewModel.loginEmailError.observeForever { error -> loginEmail.error = error }
        viewModel.loginPasswordError.observeForever { error -> loginPassword.error = error }

        viewModel.isLoading.observeForever { isLoading ->
            if(isLoading){
                loginContainer.visibility = View.GONE
                loginLoading.visibility = View.VISIBLE
            }else{
                loginContainer.visibility = View.VISIBLE
                loginLoading.visibility = View.GONE
            }
        }

        viewModel.isLoggedIn.observeForever { isLoggedIn ->
            if(isLoggedIn) this@LoginBottomsheetFragment.dismiss()
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(): LoginBottomsheetFragment =
            LoginBottomsheetFragment()
    }
}