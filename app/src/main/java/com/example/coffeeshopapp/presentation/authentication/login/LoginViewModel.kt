package com.example.coffeeshopapp.presentation.authentication.login

import com.example.coffeeshopapp.presentation.base.BaseViewModel
import com.example.coffeeshopapp.presentation.utils.DefaultEventDelegate
import com.example.coffeeshopapp.presentation.utils.DefaultStateDelegate
import com.example.coffeeshopapp.presentation.utils.EventDelegate
import com.example.coffeeshopapp.presentation.utils.StateDelegate

class LoginViewModel : BaseViewModel(),
    StateDelegate<LoginViewModel.State> by DefaultStateDelegate(State.default),
    EventDelegate<LoginViewModel.Event> by DefaultEventDelegate() {

    fun loginUser() {

    }

    private fun isNotEmptyPassword(): Boolean {
        return currentState.password.isNotEmpty()
    }

    private fun isNotEmptyEmail(): Boolean {
        return currentState.email.isNotEmpty()
    }

    private fun validateFieldsNotEmpty() {
        val isFieldsNotEmpty = isNotEmptyEmail() && isNotEmptyPassword()
        currentState = currentState.copy(isEnabledLoginButton = isFieldsNotEmpty)
    }

    fun onEmailChanged(data: String) {
        currentState = currentState.copy(email = data)
        validateFieldsNotEmpty()
    }

    fun onPasswordChanged(data: String) {
        currentState = currentState.copy(password = data)
        validateFieldsNotEmpty()
    }

    fun onRememberMeCheckedChanged(data: Boolean) {
        currentState = currentState.copy(isRememberMeChecked = data)
    }

    fun onPasswordToggleChanged(data: Boolean) {
        currentState = currentState.copy(isPasswordVisible = data)
    }

    data class State(
        val email: String,
        val password: String,
        val isPasswordVisible: Boolean = false,
        val isRememberMeChecked: Boolean = false,
        val isEnabledLoginButton: Boolean = false
    ) {
        companion object {
            val default: State
                get() = State(email = "", password = "")
        }
    }

    enum class Event {
        SUCCESS
    }
}