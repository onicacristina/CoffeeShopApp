package com.example.coffeeshopapp.presentation.authentication.create_new_password

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.coffeeshopapp.R
import com.example.coffeeshopapp.databinding.FragmentCreateNewPasswordBinding
import com.example.coffeeshopapp.presentation.base.NoBottomNavigationFragment
import com.example.coffeeshopapp.presentation.utils.extensions.viewBinding

class CreateNewPasswordFragment :
    NoBottomNavigationFragment<FragmentCreateNewPasswordBinding>(R.layout.fragment_create_new_password) {

    override val viewBinding: FragmentCreateNewPasswordBinding by viewBinding(
        FragmentCreateNewPasswordBinding::bind
    )
    override val viewModel: CreateNewPasswordViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
    }

    private fun initViews() {
        viewBinding.toolbar.tvTitle.text = getString(R.string.create_new_password)
    }

    private fun initListeners() {
        viewBinding.toolbar.ivBack.setOnClickListener {
            navController.popBackStack()
        }
    }
}