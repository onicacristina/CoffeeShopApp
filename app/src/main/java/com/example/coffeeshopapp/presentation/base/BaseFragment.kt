package com.example.coffeeshopapp.presentation.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.coffeeshopapp.MainActivity

abstract class BaseFragment : Fragment {

    open var navigationVisibility = View.VISIBLE

    constructor() : super()

    constructor(@LayoutRes contentLayoutId: Int) : super(contentLayoutId)

    protected open val viewModel: ViewModel? = null

    protected val navController: NavController
        get() = NavHostFragment.findNavController(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        bottomNavigationVisibility()
    }

    private fun bottomNavigationVisibility() {
        getMainActivity()?.bottomNavigationVisibility(navigationVisibility)
    }

    protected fun getMainActivity(): MainActivity? {
        return activity?.let { mainActivity ->
            (mainActivity as MainActivity)
        }
    }
}