package com.example.coffeeshopapp.presentation.authentication.forgot_password

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.coffeeshopapp.R
import com.example.coffeeshopapp.databinding.FragmentForgotPasswordBinding
import com.example.coffeeshopapp.presentation.base.NoBottomNavigationFragment
import com.example.coffeeshopapp.presentation.utils.extensions.setOnDebounceClickListener
import com.example.coffeeshopapp.presentation.utils.extensions.viewBinding
import com.example.coffeeshopapp.presentation.utils.showDialog
import kotlinx.coroutines.launch

class ForgotPasswordFragment :
    NoBottomNavigationFragment<FragmentForgotPasswordBinding>(R.layout.fragment_forgot_password) {

    override val viewBinding: FragmentForgotPasswordBinding by viewBinding(
        FragmentForgotPasswordBinding::bind
    )
    override val viewModel: ForgotPasswordViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initViews()
        initListeners()
        initObservers()
    }

    private fun initViews() {
        viewBinding.toolbar.tvTitle.text = getString(R.string.forgot_password)
    }

    private fun initListeners() {
        viewBinding.toolbar.ivBack.setOnClickListener {
            navController.popBackStack()
        }

        viewBinding.etEmail.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                viewModel.generateNewPassword()
            }
            false
        }

        viewBinding.etEmail.doAfterTextChanged { email ->
            viewModel.onEmailChanged(email = email.toString())
        }

        viewBinding.btnContinue.setOnDebounceClickListener {
            viewModel.generateNewPassword()
        }
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                launch {
                    viewModel.state.collect { value ->
                        renderState(value)
                    }
                }
            }
        }
    }

    private fun renderState(state: ForgotPasswordViewModel.State) {
        updateResetButton(state.isButtonEnabled)
    }

    private fun updateResetButton(buttonEnabled: Boolean) {
        viewBinding.btnContinue.isEnabled = buttonEnabled
    }

    private fun showResetSuccessfulDialog() {
        showDialog(
            context = requireContext(),
            title = getString(R.string.email_was_sent),
            description = getString(R.string.email_sent),
            positiveActionText = getString(R.string.btn_ok),
            negativeActionText = getString(R.string.btn_cancel),
            negativeAction = {},
            positiveAction = {},
            isCancellable = true,
            positiveActionButtonColor = R.drawable.round_button
        )
    }
}