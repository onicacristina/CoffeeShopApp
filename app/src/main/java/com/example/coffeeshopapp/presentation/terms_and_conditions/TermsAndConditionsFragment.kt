package com.example.coffeeshopapp.presentation.terms_and_conditions

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.coffeeshopapp.R
import com.example.coffeeshopapp.databinding.FragmentTermsAndConditionsBinding
import com.example.coffeeshopapp.presentation.authentication.create_new_password.CreateNewPasswordViewModel
import com.example.coffeeshopapp.presentation.base.NoBottomNavigationFragment
import com.example.coffeeshopapp.presentation.utils.extensions.viewBinding

class TermsAndConditionsFragment :
    NoBottomNavigationFragment<FragmentTermsAndConditionsBinding>(R.layout.fragment_terms_and_conditions) {

    override val viewBinding: FragmentTermsAndConditionsBinding by viewBinding(
        FragmentTermsAndConditionsBinding::bind
    )
    override val viewModel: CreateNewPasswordViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
    }

    private fun initViews() {
        viewBinding.toolbar.tvTitle.text = getString(R.string.terms_and_conditions)
    }

    private fun initListeners() {
        viewBinding.toolbar.ivBack.setOnClickListener {
            navController.popBackStack()
        }
    }
}