package com.example.coffeeshopapp.presentation.authentication.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.SpannableString
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.coffeeshopapp.R
import com.example.coffeeshopapp.databinding.FragmentHomeBinding
import com.example.coffeeshopapp.databinding.FragmentLoginBinding
import com.example.coffeeshopapp.presentation.base.BaseViewBindingFragment
import com.example.coffeeshopapp.presentation.base.NoBottomNavigationFragment
import com.example.coffeeshopapp.presentation.home.HomeViewModel
import com.example.coffeeshopapp.presentation.utils.extensions.addClickableLink
import com.example.coffeeshopapp.presentation.utils.extensions.viewBinding

class LoginFragment :
    NoBottomNavigationFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    override val viewBinding: FragmentLoginBinding by viewBinding(FragmentLoginBinding::bind)
    override val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
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

    }
}