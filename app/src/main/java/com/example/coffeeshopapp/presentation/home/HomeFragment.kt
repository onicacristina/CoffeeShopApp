package com.example.coffeeshopapp.presentation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.coffeeshopapp.R
import com.example.coffeeshopapp.databinding.FragmentHomeBinding
import com.example.coffeeshopapp.presentation.base.BaseViewBindingFragment
import com.example.coffeeshopapp.presentation.utils.extensions.viewBinding

class HomeFragment :
    BaseViewBindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    override val viewBinding: FragmentHomeBinding by viewBinding(FragmentHomeBinding::bind)
    override val viewModel: HomeViewModel by viewModels()

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