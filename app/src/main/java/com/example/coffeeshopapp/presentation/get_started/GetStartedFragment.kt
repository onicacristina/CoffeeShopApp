package com.example.coffeeshopapp.presentation.get_started

import android.os.Bundle
import android.view.View
import com.example.coffeeshopapp.R
import com.example.coffeeshopapp.databinding.FragmentGetStartedBinding
import com.example.coffeeshopapp.presentation.base.NoBottomNavigationFragment
import com.example.coffeeshopapp.presentation.utils.extensions.viewBinding

class GetStartedFragment :
    NoBottomNavigationFragment<FragmentGetStartedBinding>(R.layout.fragment_get_started) {

    override val viewBinding: FragmentGetStartedBinding by viewBinding(FragmentGetStartedBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
    }

    private fun initViews() {
    }

    private fun initListeners() {
        viewBinding.btnGetStarted.setOnClickListener {
            navController.navigate(R.id.action_getStartedFragment_to_loginFragment)
        }
    }
}