package com.gabez.pathvisionapp.app.view.settings.authentication.register

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

class RegisterBottomsheetFragment: BottomSheetDialogFragment(), KoinComponent {

    private lateinit var registerEmail: TextInputEditText
    private lateinit var registerPassword: TextInputEditText
    private lateinit var registerUsername: TextInputEditText

    private lateinit var registerBtn: MaterialButton
    private lateinit var cancelBtn: MaterialButton

    private lateinit var registerContainer: ConstraintLayout
    private lateinit var registerLoading: ProgressBar

    private val viewModel: RegisterViewModel by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_bottomsheet_register, container, false)

        cancelBtn = view.findViewById(R.id.buttonCancel)
        cancelBtn.setOnClickListener { this@RegisterBottomsheetFragment.dismiss() }

        registerEmail = view.findViewById(R.id.registerEmail)
        registerPassword = view.findViewById(R.id.registerPassword)
        registerUsername = view.findViewById(R.id.registerUsername)

        registerContainer = view.findViewById(R.id.registerContainer)
        registerLoading = view.findViewById(R.id.registerLoading)

        registerBtn = view.findViewById(R.id.buttonRegister)
        registerBtn.setOnClickListener { viewModel.registerUser(registerEmail.text.toString(), registerUsername.text.toString(), registerPassword.text.toString()) }

        viewModel.authenticationError.observeForever { error -> registerEmail.error = error }
        viewModel.registerEmailError.observeForever { error -> registerEmail.error = error }

        viewModel.registerPasswordError.observeForever { error -> registerPassword.error = error }
        viewModel.registerUsernameError.observeForever { error -> registerUsername.error = error }

        viewModel.isLoading.observeForever { isLoading ->
            if(isLoading){
                registerContainer.visibility = View.GONE
                registerLoading.visibility = View.VISIBLE
            }else{
                registerContainer.visibility = View.VISIBLE
                registerLoading.visibility = View.GONE
            }
        }

        viewModel.isLoggedIn.observeForever { isLoggedIn ->
            if(isLoggedIn) this@RegisterBottomsheetFragment.dismiss()
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(): RegisterBottomsheetFragment =
            RegisterBottomsheetFragment()
    }
}