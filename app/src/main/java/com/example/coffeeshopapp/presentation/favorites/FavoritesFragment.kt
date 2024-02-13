package com.example.coffeeshopapp.presentation.favorites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.coffeeshopapp.R
import com.example.coffeeshopapp.databinding.FragmentFavoritesBinding
import com.example.coffeeshopapp.presentation.base.BaseViewBindingFragment
import com.example.coffeeshopapp.presentation.utils.extensions.viewBinding

class FavoritesFragment :
    BaseViewBindingFragment<FragmentFavoritesBinding>(R.layout.fragment_favorites) {

    override val viewBinding: FragmentFavoritesBinding by viewBinding(FragmentFavoritesBinding::bind)
    override val viewModel: FavoritesViewModel by viewModels()

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