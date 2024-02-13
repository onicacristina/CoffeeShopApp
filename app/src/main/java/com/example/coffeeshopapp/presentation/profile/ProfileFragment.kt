package com.example.coffeeshopapp.presentation.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.coffeeshopapp.R
import com.example.coffeeshopapp.databinding.FragmentProfileBinding
import com.example.coffeeshopapp.presentation.base.BaseViewBindingFragment
import com.example.coffeeshopapp.presentation.utils.extensions.viewBinding

class ProfileFragment : BaseViewBindingFragment<FragmentProfileBinding>(R.layout.fragment_profile) {

    override val viewBinding: FragmentProfileBinding by viewBinding(FragmentProfileBinding::bind)
    override val viewModel: ProfileViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
    }

    private fun initViews() {
    }

    private fun initListeners() {

    }
}