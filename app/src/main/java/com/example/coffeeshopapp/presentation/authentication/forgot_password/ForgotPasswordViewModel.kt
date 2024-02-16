package com.example.coffeeshopapp.presentation.authentication.forgot_password

import com.example.coffeeshopapp.presentation.base.BaseViewModel
import com.example.coffeeshopapp.presentation.utils.DefaultEventDelegate
import com.example.coffeeshopapp.presentation.utils.DefaultStateDelegate
import com.example.coffeeshopapp.presentation.utils.EventDelegate
import com.example.coffeeshopapp.presentation.utils.StateDelegate

class ForgotPasswordViewModel : BaseViewModel(),
    StateDelegate<ForgotPasswordViewModel.State> by DefaultStateDelegate(State.default),
    EventDelegate<ForgotPasswordViewModel.Event> by DefaultEventDelegate() {

    fun generateNewPassword() {

    }

    private fun isNotEmptyEmail(): Boolean {
        return currentState.email.isNotEmpty()
    }

    fun onEmailChanged(email: String) {
        currentState = currentState.copy(email = email)
        validateButton()
    }

    private fun validateButton() {
        currentState = currentState.copy(isButtonEnabled = isNotEmptyEmail())
    }

    data class State(
        val email: String,
        val isButtonEnabled: Boolean
    ) {
        companion object {
            val default: State
                get() = State(
                    email = "",
                    isButtonEnabled = false
                )
        }
    }

    enum class Event {
        SUCCESS
    }
}