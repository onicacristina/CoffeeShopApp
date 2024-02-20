package com.example.coffeeshopapp.presentation.authentication.register

import android.os.Bundle
import android.text.SpannableString
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.coffeeshopapp.R
import com.example.coffeeshopapp.databinding.FragmentRegisterBinding
import com.example.coffeeshopapp.presentation.base.NoBottomNavigationFragment
import com.example.coffeeshopapp.presentation.utils.extensions.addClickableLink
import com.example.coffeeshopapp.presentation.utils.extensions.setOnDebounceClickListener
import com.example.coffeeshopapp.presentation.utils.extensions.viewBinding
import com.example.coffeeshopapp.presentation.utils.showDialog
import kotlinx.coroutines.launch

class RegisterFragment :
    NoBottomNavigationFragment<FragmentRegisterBinding>(R.layout.fragment_register) {

    override val viewBinding: FragmentRegisterBinding by viewBinding(FragmentRegisterBinding::bind)
    override val viewModel: RegisterViewModel by viewModels()

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
        viewBinding.toolbar.tvTitle.text = getString(R.string.sign_up)
        viewBinding.tvAlreadyHaveAccount.addClickableLink(
            fullText = getString(R.string.already_have_an_account),
            linkText = SpannableString(getString(R.string.sign_in)),
            context = requireContext(),
            isBolded = true,
            isUnderlined = true,
            textColor = R.color.orange
        ) {
            navController.navigate(R.id.action_registerFragment_to_loginFragment)
        }
        viewBinding.cbTermsAndConditions.addClickableLink(
            fullText = getString(R.string.agree_to_the_terms_and_conditions),
            linkText = SpannableString(getString(R.string.terms_and_conditions)),
            context = requireContext(),
            isBolded = true,
            isUnderlined = true,
            textColor = R.color.orange
        ) {
            navController.navigate(R.id.action_registerFragment_to_termsAndConditionsFragment)
        }
    }

    private fun initListeners() {
        viewBinding.etName.doAfterTextChanged {
            viewModel.onNameChanged(it.toString())
        }
        viewBinding.etEmail.doAfterTextChanged {
            viewModel.onEmailChanged(it.toString())
        }
        viewBinding.etPassword.doAfterTextChanged {
            viewModel.onPasswordChanged(it.toString())
        }
        viewBinding.cbTermsAndConditions.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onTermsCheckedChanged(isChecked)
        }
        viewBinding.btnSignUp.setOnDebounceClickListener {
            viewModel.registerUser()
        }
        initTogglePasswordMask(false)
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

    private fun onEvent(event: RegisterViewModel.Event) {
        when (event) {
            RegisterViewModel.Event.SUCCESS -> {

            }

            RegisterViewModel.Event.SHORT_PASSWORD -> {
                showInfoOrErrorDialog(
                    title = getString(R.string.something_went_wrong),
                    description = getString(R.string.password_length_rule)
                )
            }

            RegisterViewModel.Event.PASSWORD_ONE_UPPERCASE_AND_ONE_NUMBER -> {
                showInfoOrErrorDialog(
                    title = getString(R.string.something_went_wrong),
                    description = getString(R.string.password_rules)
                )
            }
        }
    }

    private fun render(state: RegisterViewModel.State) {
        initTogglePasswordMask(state.isPasswordVisible)
        updateRegisterButton(state.isEnabledRegisterButton)
    }

    private fun initTogglePasswordMask(isPasswordVisible: Boolean) {
        viewBinding.btnTogglePasswordMask.setOnClickListener {
            togglePasswordMask(
                viewBinding.etPassword,
                viewBinding.btnTogglePasswordMask,
                !isPasswordVisible
            )
        }
    }

    private fun togglePasswordMask(editText: EditText, imageButton: ImageButton, show: Boolean) {
        editText.transformationMethod =
            if (show) HideReturnsTransformationMethod.getInstance() else PasswordTransformationMethod.getInstance()
        imageButton.setImageResource(if (show) R.drawable.ic_eye_open else R.drawable.ic_eye_closed)
        viewModel.onPasswordToggleChanged(show)
        editText.setSelection(editText.length())
    }

    private fun updateRegisterButton(isEnabled: Boolean) {
        viewBinding.btnSignUp.isEnabled = isEnabled
    }

    private fun showInfoOrErrorDialog(title: String, description: String) {
        showDialog(
            context = requireContext(),
            title = title,
            description = description,
            positiveActionButtonColor = R.drawable.round_button,
            positiveActionText = getString(R.string.btn_ok),
            negativeActionText = getString(R.string.btn_cancel),
            negativeAction = {},
            positiveAction = {},
            isCancellable = true,
        )
    }
}