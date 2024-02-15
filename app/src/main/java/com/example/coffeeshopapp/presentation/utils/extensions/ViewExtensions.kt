package com.example.coffeeshopapp.presentation.utils.extensions

import android.view.View
import com.example.coffeeshopapp.presentation.utils.DebounceClickListener

fun View.setOnDebounceClickListener(clickListener: (View) -> Unit) {
    this.setOnClickListener(object : DebounceClickListener() {
        override fun onDebouncedClick(v: View) {
            clickListener(v)
        }
    })
}