package com.example.coffeeshopapp.presentation.authentication.create_new_password

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.coffeeshopapp.R
import com.example.coffeeshopapp.databinding.FragmentCreateNewPasswordBinding
import com.example.coffeeshopapp.presentation.base.NoBottomNavigationFragment
import com.example.coffeeshopapp.presentation.utils.extensions.setOnDebounceClickListener
import com.example.coffeeshopapp.presentation.utils.extensions.viewBinding
import com.example.coffeeshopapp.presentation.utils.showDialog
import kotlinx.coroutines.launch

class CreateNewPasswordFragment :
    NoBottomNavigationFragment<FragmentCreateNewPasswordBinding>(R.layout.fragment_create_new_password) {

    override val viewBinding: FragmentCreateNewPasswordBinding by viewBinding(
        FragmentCreateNewPasswordBinding::bind
    )
    override val viewModel: CreateNewPasswordViewModel by viewModels()

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
        viewBinding.toolbar.tvTitle.text = getString(R.string.create_new_password)
    }

    private fun initListeners() {
        viewBinding.toolbar.ivBack.setOnClickListener {
            navController.popBackStack()
        }
        viewBinding.etNewPassword.doAfterTextChanged {
            viewModel.onNewPasswordChanged(it.toString())
        }
        viewBinding.etConfirmNewPassword.doAfterTextChanged {
            viewModel.onConfirmNewPasswordChanged(it.toString())
        }
        viewBinding.etConfirmNewPassword.doAfterTextChanged {
            viewModel.onConfirmNewPasswordChanged(it.toString())
        }
        viewBinding.btnSave.setOnDebounceClickListener {
            viewModel.changePassword()
        }
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                launch {
                    viewModel.state.collect { value ->
                        render(value)
                    }
                }
                launch {
                    viewModel.event.collect { value ->
                        onEvent(value)
                    }
                }
            }
        }
    }

    private fun onEvent(event: CreateNewPasswordViewModel.Event) {
        when (event) {
            CreateNewPasswordViewModel.Event.SUCCESS -> {}
            CreateNewPasswordViewModel.Event.SHORT_PASSWORD -> {
                showInfoOrErrorDialog(
                    title = getString(R.string.something_went_wrong),
                    description = getString(R.string.password_length_rule)
                )
            }

            CreateNewPasswordViewModel.Event.NO_MATCH_PASSWORD -> {
                showInfoOrErrorDialog(
                    title = getString(R.string.something_went_wrong),
                    description = getString(R.string.passwords_do_not_match)
                )
            }

            CreateNewPasswordViewModel.Event.CHANGE_FAILURE -> {}
            CreateNewPasswordViewModel.Event.PASSWORD_ONE_UPPERCASE_AND_ONE_NUMBER -> {
                showInfoOrErrorDialog(
                    title = getString(R.string.something_went_wrong),
                    description = getString(R.string.password_rules)
                )
            }
        }
    }

    private fun render(state: CreateNewPasswordViewModel.State) {
        updateSaveButton(state.isEnabledSaveButton)
    }

    private fun updateSaveButton(isActive: Boolean) {
        viewBinding.btnSave.isEnabled = isActive
    }

    private fun showInfoOrErrorDialog(title: String, description: String) {
        showDialog(
            context = requireContext(),
            title = title,
            description = description,
            positiveActionText = getString(R.string.btn_ok),
            negativeActionText = getString(R.string.btn_cancel),
            negativeAction = {},
            positiveAction = {},
            isCancellable = true,
            positiveActionButtonColor = R.drawable.round_button
        )
    }
}