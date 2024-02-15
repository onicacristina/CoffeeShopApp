package com.example.coffeeshopapp.presentation.authentication.register

import android.os.Bundle
import android.text.SpannableString
import android.view.View
import androidx.fragment.app.viewModels
import com.example.coffeeshopapp.R
import com.example.coffeeshopapp.databinding.FragmentRegisterBinding
import com.example.coffeeshopapp.presentation.authentication.login.LoginViewModel
import com.example.coffeeshopapp.presentation.base.NoBottomNavigationFragment
import com.example.coffeeshopapp.presentation.utils.extensions.addClickableLink
import com.example.coffeeshopapp.presentation.utils.extensions.viewBinding

class RegisterFragment :
    NoBottomNavigationFragment<FragmentRegisterBinding>(R.layout.fragment_register) {

    override val viewBinding: FragmentRegisterBinding by viewBinding(FragmentRegisterBinding::bind)
    override val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
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

    }
}