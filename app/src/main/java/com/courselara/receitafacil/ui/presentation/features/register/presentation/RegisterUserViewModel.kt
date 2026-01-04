package com.courselara.receitafacil.ui.presentation.features.register.presentation

import androidx.lifecycle.ViewModel
import com.courselara.receitafacil.core.sideeffects.SideEffect
import com.courselara.receitafacil.core.util.Constants
import com.courselara.receitafacil.ui.presentation.features.register.domain.model.RegisterInputValidationType
import com.courselara.receitafacil.ui.presentation.features.register.domain.usecase.RegisterUserUserCase
import com.courselara.receitafacil.ui.presentation.features.register.domain.usecase.ValidateRegisterInputUseCase
import com.courselara.receitafacil.ui.presentation.features.register.presentation.state.RegisterUserState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class RegisterUserViewModel @Inject constructor(
    private val registerUserUserCase: RegisterUserUserCase,
    private val validateRegisterInputUseCase: ValidateRegisterInputUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUserState())
    var uiState = _uiState.asStateFlow()

    private val _sideEffectChannel = Channel<SideEffect>(capacity = Channel.BUFFERED)
    var sideEffect = _sideEffectChannel.receiveAsFlow()

    fun onNameInputChange(newValue: String) {
        _uiState.update { it.copy(nameValue = newValue) }
        checkInputValidation()
    }
    fun onEmailInputChange(newValue: String) {
        _uiState.update { it.copy(emailValue = newValue) }
        checkInputValidation()
    }
    fun onPhoneNumberInputChange(newValue: String) {
        _uiState.update { it.copy(phoneValue = newValue) }
        checkInputValidation()
    }
    fun onPasswordInputChange(newValue: String) {
        _uiState.update { it.copy(passwordValue = newValue) }
        checkInputValidation()
    }
    fun onPasswordRepeatedInputChange(newValue: String) {
        _uiState.update { it.copy(passwordRepeatedValue = newValue) }
        checkInputValidation()
    }
    fun onToggleVisualTransformationPassword() {
        _uiState.update { it.copy(isPasswordShow = !it.isPasswordShow) }
    }
    fun onToggleVisualTransformationPasswordRepeated() {
        _uiState.update { it.copy(isPasswordRepeatedShow = !it.isPasswordRepeatedShow) }
    }

    private fun checkInputValidation() {
        val validationResult = validateRegisterInputUseCase(

            name = uiState.value.nameValue,
            email = uiState.value.emailValue,
            phone = uiState.value.phoneValue,
            password = uiState.value.passwordValue,
            passwordRepeated = uiState.value.passwordRepeatedValue
        )
        processInputValidationType(validationResult)
    }

    private fun processInputValidationType(type: RegisterInputValidationType) {
        _uiState.update {
            when (type) {
                RegisterInputValidationType.EmptyField -> {
                    it.copy(
                        errorMessageInput = Constants.ValidationAuthMessages.EMPTY_FIELD,
                        isInputValid = false
                    )
                }

                RegisterInputValidationType.NoEmail -> {
                    it.copy(
                        errorMessageInput = Constants.ValidationAuthMessages.INVALID_EMAIL,
                        isInputValid = false
                    )
                }

                RegisterInputValidationType.PasswordTooShort -> {
                    it.copy(
                        errorMessageInput = Constants.ValidationAuthMessages.PASSWORD_TOO_SHORT,
                        isInputValid = false
                    )
                }

                RegisterInputValidationType.PasswordsDoNotMatch -> {
                    it.copy(
                        errorMessageInput = Constants.ValidationAuthMessages.PASSWORDS_DO_NOT_MATCH,
                        isInputValid = false
                    )
                }

                RegisterInputValidationType.PasswordUpperCaseMissing -> {
                    it.copy(
                        errorMessageInput = Constants.ValidationAuthMessages.PASSWORD_UPPERCASE_MISSING,
                        isInputValid = false
                    )
                }

                RegisterInputValidationType.PasswordSpecialCharMissing -> {
                    it.copy(
                        errorMessageInput = Constants.ValidationAuthMessages.PASSWORD_SPECIAL_CHAR_MISSING,
                        isInputValid = false
                    )
                }

                RegisterInputValidationType.PasswordNumberMissing -> {
                    it.copy(
                        errorMessageInput = Constants.ValidationAuthMessages.PASSWORD_NUMBER_MISSING,
                        isInputValid = false
                    )
                }

                RegisterInputValidationType.PhoneNumberInvalid -> {
                    it.copy(
                        errorMessageInput = Constants.ValidationAuthMessages.PHONE_NUMBER_INVALID,
                        isInputValid = false
                    )
                }

                RegisterInputValidationType.Valid -> {
                    it.copy(
                        errorMessageInput = null,
                        isInputValid = true
                    )
                }
            }
        }
    }

}