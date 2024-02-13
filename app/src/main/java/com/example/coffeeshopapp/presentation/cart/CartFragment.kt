package com.example.coffeeshopapp.presentation.cart

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.coffeeshopapp.R
import com.example.coffeeshopapp.databinding.FragmentCartBinding
import com.example.coffeeshopapp.presentation.base.BaseViewBindingFragment
import com.example.coffeeshopapp.presentation.utils.extensions.viewBinding

class CartFragment :
    BaseViewBindingFragment<FragmentCartBinding>(R.layout.fragment_cart) {

    override val viewBinding: FragmentCartBinding by viewBinding(FragmentCartBinding::bind)
    override val viewModel: CartViewModel by viewModels()

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