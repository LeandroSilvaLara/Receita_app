package com.courselara.receitafacil.ui.presentation.features.auth.login.presentation

import androidx.lifecycle.ViewModel
import com.courselara.receitafacil.core.sideeffects.SideEffect
import com.courselara.receitafacil.core.util.Constants
import com.courselara.receitafacil.ui.presentation.features.auth.login.domain.model.LoginInputValidationType
import com.courselara.receitafacil.ui.presentation.features.auth.login.domain.usecase.ValidateLoginInputUseCase
import com.courselara.receitafacil.ui.presentation.features.auth.login.presentation.state.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val validateLoginInputUseCase: ValidateLoginInputUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    private val _sideEffectChannel = Channel<SideEffect>(capacity = Channel.BUFFERED)
    val sideEffectChannel = _sideEffectChannel.receiveAsFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnEmailChange -> {
                _uiState.update { it.copy(emailValue = event.email) }
                checkInputValidation()
            }

            is LoginEvent.OnPasswordChange -> {
                _uiState.update { it.copy(passwordValue = event.password) }
                checkInputValidation()
            }

            LoginEvent.OnToggleVisualTransformationPassword -> {
                _uiState.update { it.copy(isPasswordShow = !_uiState.value.isPasswordShow) }
            }

            LoginEvent.OnLoginClick -> onLoginClick()
        }
    }

    private fun onLoginClick() {

    }

    private fun checkInputValidation() {
        val validateResult = validateLoginInputUseCase(
            email = _uiState.value.emailValue,
            password = _uiState.value.passwordValue
        )
        processInputValidationType(validateResult)
    }

    private fun processInputValidationType(type: LoginInputValidationType) {
        _uiState.update {
            when (type) {
                LoginInputValidationType.EmptyField -> {
                    it.copy(
                        errorMessageLoginProcess = Constants.ValidationAuthMessages.EMPTY_FIELD,
                        isInputValid = false
                    )
                }

                LoginInputValidationType.NoEmail -> {
                    it.copy(
                        errorMessageLoginProcess = Constants.ValidationAuthMessages.INVALID_EMAIL,
                        isInputValid = false
                    )
                }

                LoginInputValidationType.Valid -> {
                    it.copy(
                        errorMessageLoginProcess = null,
                        isInputValid = true
                    )
                }
            }
        }
    }


    private fun saveLocalStorageData(token: String, userName: String) {

    }

}