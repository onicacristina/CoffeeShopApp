package com.example.coffeeshopapp.presentation.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.coffeeshopapp.R
import com.example.coffeeshopapp.presentation.utils.extensions.setOnDebounceClickListener

fun showDialog(
    context: Context,
    title: String,
    description: String,
    positiveActionButtonColor: Int,
    positiveActionText: String,
    positiveAction: (() -> Unit)?,
    negativeActionText: String,
    negativeAction: (() -> Unit)?,
    isCancellable: Boolean = true
) {
    val dialog = Dialog(context, R.style.CustomDialog)
    dialog.setContentView(R.layout.layout_popup)
    dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    dialog.window?.setLayout(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )
    dialog.findViewById<TextView>(R.id.tvPopUpTitle).text = title
    dialog.findViewById<TextView>(R.id.tvPopUpDescription).text = description
    dialog.findViewById<Button>(R.id.btnPositive).setBackgroundResource(positiveActionButtonColor)
    dialog.findViewById<Button>(R.id.btnPositive).text = positiveActionText
    dialog.findViewById<Button>(R.id.btnPositive).setOnDebounceClickListener {
        if (positiveAction != null) {
            positiveAction()
        }
        dialog.dismiss()
    }
    dialog.findViewById<Button>(R.id.btnNegative).text = negativeActionText
    dialog.findViewById<Button>(R.id.btnNegative).setOnDebounceClickListener {
        if (negativeAction != null) {
            negativeAction()
        }
        dialog.dismiss()
    }
    dialog.setCancelable(isCancellable)
    dialog.show()
}