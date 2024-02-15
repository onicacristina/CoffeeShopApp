package com.example.coffeeshopapp.presentation.authentication.forgot_password

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.coffeeshopapp.R
import com.example.coffeeshopapp.databinding.FragmentForgotPasswordBinding
import com.example.coffeeshopapp.presentation.base.NoBottomNavigationFragment
import com.example.coffeeshopapp.presentation.utils.extensions.viewBinding

class ForgotPasswordFragment :
    NoBottomNavigationFragment<FragmentForgotPasswordBinding>(R.layout.fragment_forgot_password) {

    override val viewBinding: FragmentForgotPasswordBinding by viewBinding(
        FragmentForgotPasswordBinding::bind
    )
    override val viewModel: ForgotPasswordViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
    }

    private fun initViews() {
        viewBinding.toolbar.tvTitle.text = getString(R.string.forgot_password)
    }

    private fun initListeners() {
        viewBinding.toolbar.ivBack.setOnClickListener {
            navController.popBackStack()
        }
    }
}