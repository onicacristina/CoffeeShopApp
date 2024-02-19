package com.example.coffeeshopapp.presentation.authentication.create_new_password

import androidx.lifecycle.viewModelScope
import com.example.coffeeshopapp.presentation.base.BaseViewModel
import com.example.coffeeshopapp.presentation.utils.DefaultEventDelegate
import com.example.coffeeshopapp.presentation.utils.DefaultStateDelegate
import com.example.coffeeshopapp.presentation.utils.EventDelegate
import com.example.coffeeshopapp.presentation.utils.StateDelegate
import kotlinx.coroutines.launch

class CreateNewPasswordViewModel : BaseViewModel(),
    StateDelegate<CreateNewPasswordViewModel.State> by DefaultStateDelegate(State.default),
    EventDelegate<CreateNewPasswordViewModel.Event> by DefaultEventDelegate() {

    fun changePassword() {
        viewModelScope.launch {
            val newPassword = currentState.newPassword
            val confirmNewPassword = currentState.confirmNewPassword

            if (!isCompletedCorrect(newPassword, confirmNewPassword)){
                return@launch
            }
        }

    }

    private fun isCompletedCorrect(
        newPassword: String,
        confirmNewPassword: String
    ): Boolean {
        return when {
            newPassword.length < 8 || confirmNewPassword.length < 8 -> {
                sendEvent(Event.SHORT_PASSWORD)
                false
            }

            !isOneUpperCaseLetterInPassword() && !isOneNumberInPassword() -> {
                sendEvent(Event.PASSWORD_ONE_UPPERCASE_AND_ONE_NUMBER)
                false
            }

            newPassword != confirmNewPassword -> {
                sendEvent(Event.NO_MATCH_PASSWORD)
                false
            }

            else -> true
        }
    }

    private fun isOneNumberInPassword(): Boolean {
        return currentState.newPassword.any { it.isDigit() }
    }

    private fun isOneUpperCaseLetterInPassword(): Boolean {
        return currentState.newPassword.any { it.isUpperCase() }
    }

    private fun isNotEmptyNewPassword(): Boolean {
        return currentState.newPassword.isNotEmpty()
    }

    private fun isNotEmptyConfirmNewPassword(): Boolean {
        return currentState.confirmNewPassword.isNotEmpty()
    }

    private fun validateFieldsNotEmpty() {
        val isFieldsNotEmpty = isNotEmptyNewPassword() && isNotEmptyConfirmNewPassword()
        currentState = currentState.copy(isEnabledSaveButton = isFieldsNotEmpty)
    }

    fun onNewPasswordChanged(data: String) {
        currentState = currentState.copy(newPassword = data)
        validateFieldsNotEmpty()
    }

    fun onConfirmNewPasswordChanged(data: String) {
        currentState = currentState.copy(confirmNewPassword = data)
        validateFieldsNotEmpty()
    }

    data class State(
        val newPassword: String,
        val confirmNewPassword: String,
        val isEnabledSaveButton: Boolean = false
    ) {
        companion object {
            val default: State
                get() = State(newPassword = "", confirmNewPassword = "")
        }
    }

    enum class Event {
        SUCCESS,
        SHORT_PASSWORD,
        NO_MATCH_PASSWORD,
        CHANGE_FAILURE,
        PASSWORD_ONE_UPPERCASE_AND_ONE_NUMBER
    }
}