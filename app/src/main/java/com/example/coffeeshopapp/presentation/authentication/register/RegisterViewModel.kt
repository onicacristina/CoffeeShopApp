package com.example.coffeeshopapp.presentation.authentication.register

import androidx.lifecycle.viewModelScope
import com.example.coffeeshopapp.presentation.base.BaseViewModel
import com.example.coffeeshopapp.presentation.utils.DefaultEventDelegate
import com.example.coffeeshopapp.presentation.utils.DefaultStateDelegate
import com.example.coffeeshopapp.presentation.utils.EventDelegate
import com.example.coffeeshopapp.presentation.utils.StateDelegate
import kotlinx.coroutines.launch

class RegisterViewModel : BaseViewModel(),
    StateDelegate<RegisterViewModel.State> by DefaultStateDelegate(State.default),
    EventDelegate<RegisterViewModel.Event> by DefaultEventDelegate() {

    fun registerUser() {
        viewModelScope.launch {
            if (!isPasswordCompletedCorrect(currentState.password)) {
                return@launch
            }
        }
    }

    private fun isNotEmptyFullName(): Boolean {
        return currentState.name.isNotEmpty()
    }

    private fun isNotEmptyPassword(): Boolean {
        return currentState.password.isNotEmpty()
    }

    private fun isNotEmptyEmail(): Boolean {
        return currentState.email.isNotEmpty()
    }

    private fun isOneUpperCaseLetterInPassword(): Boolean {
        return currentState.password.any { it.isUpperCase() }
    }

    private fun isOneNumberInPassword(): Boolean {
        return currentState.password.any { it.isDigit() }
    }

    private fun hasMin8Chars(): Boolean {
        return currentState.password.length >= 8
    }

    private fun validateFieldsNotEmpty() {
        val isAcceptedTermsAndConditions = currentState.isTermsChecked
        val isFieldsNotEmpty =
            isNotEmptyEmail() && isNotEmptyEmail() && isNotEmptyPassword() && isAcceptedTermsAndConditions
        currentState = currentState.copy(isEnabledRegisterButton = isFieldsNotEmpty)
    }

    private fun isPasswordCompletedCorrect(
        password: String
    ): Boolean {
        return when {
            hasMin8Chars() -> {
                sendEvent(RegisterViewModel.Event.SHORT_PASSWORD)
                false
            }

            !isOneUpperCaseLetterInPassword() && !isOneNumberInPassword() -> {
                sendEvent(RegisterViewModel.Event.PASSWORD_ONE_UPPERCASE_AND_ONE_NUMBER)
                false
            }

            else -> true
        }
    }

    fun onNameChanged(data: String) {
        currentState = currentState.copy(name = data)
        validateFieldsNotEmpty()
    }

    fun onEmailChanged(data: String) {
        currentState = currentState.copy(email = data)
        validateFieldsNotEmpty()
    }

    fun onPasswordChanged(data: String) {
        currentState = currentState.copy(password = data)
        validateFieldsNotEmpty()
    }

    fun onTermsCheckedChanged(data: Boolean) {
        currentState = currentState.copy(isTermsChecked = data)
    }

    fun onPasswordToggleChanged(data: Boolean) {
        currentState = currentState.copy(isPasswordVisible = data)
    }

    data class State(
        val name: String,
        val email: String,
        val password: String,
        val isPasswordVisible: Boolean = false,
        val isTermsChecked: Boolean = false,
        val isEnabledRegisterButton: Boolean = false
    ) {
        companion object {
            val default: State
                get() = State(name = "", email = "", password = "")
        }
    }

    enum class Event {
        SUCCESS,
        SHORT_PASSWORD,
        PASSWORD_ONE_UPPERCASE_AND_ONE_NUMBER
    }
}