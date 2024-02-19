package com.example.coffeeshopapp.presentation.authentication.login

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
import com.example.coffeeshopapp.databinding.FragmentLoginBinding
import com.example.coffeeshopapp.presentation.base.NoBottomNavigationFragment
import com.example.coffeeshopapp.presentation.utils.extensions.addClickableLink
import com.example.coffeeshopapp.presentation.utils.extensions.setOnDebounceClickListener
import com.example.coffeeshopapp.presentation.utils.extensions.viewBinding
import kotlinx.coroutines.launch

class LoginFragment :
    NoBottomNavigationFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    override val viewBinding: FragmentLoginBinding by viewBinding(FragmentLoginBinding::bind)
    override val viewModel: LoginViewModel by viewModels()

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
        viewBinding.tvForgotPassword.addClickableLink(
            fullText = getString(R.string.forgot_password),
            linkText = SpannableString(getString(R.string.forgot_password)),
            context = requireContext(),
            isBolded = true,
            isUnderlined = true,
            textColor = R.color.orange
        ) {
            navController.navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
        }

        viewBinding.tvDoNotHaveAccount.addClickableLink(
            fullText = getString(R.string.do_not_have_an_account),
            linkText = SpannableString(getString(R.string.sign_up)),
            context = requireContext(),
            isBolded = true,
            isUnderlined = true,
            textColor = R.color.orange
        ) {
            navController.navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun initListeners() {
        viewBinding.etEmail.doAfterTextChanged {
            viewModel.onEmailChanged(it.toString())
        }
        viewBinding.etPassword.doAfterTextChanged {
            viewModel.onPasswordChanged(it.toString())
        }
        viewBinding.cbRememberMe.setOnCheckedChangeListener { _, isChecked ->
            viewModel.onRememberMeCheckedChanged(isChecked)
        }
        viewBinding.btnSignIn.setOnDebounceClickListener {
            viewModel.loginUser()
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

    private fun onEvent(event: LoginViewModel.Event) {

    }

    private fun render(state: LoginViewModel.State) {
        initTogglePasswordMask(state.isPasswordVisible)
        updateLoginButton(state.isEnabledLoginButton)
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

    private fun updateLoginButton(isEnabled: Boolean) {
        viewBinding.btnSignIn.isEnabled = isEnabled
    }
}