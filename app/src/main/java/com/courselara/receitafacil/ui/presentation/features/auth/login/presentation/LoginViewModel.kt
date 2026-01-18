package com.courselara.receitafacil.ui.presentation.features.auth.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.courselara.receitafacil.core.sideeffects.SideEffect
import com.courselara.receitafacil.core.util.Constants
import com.courselara.receitafacil.core.util.extensions.observeState
import com.courselara.receitafacil.ui.presentation.features.auth.login.domain.model.AuthUserRequestModel
import com.courselara.receitafacil.ui.presentation.features.auth.login.domain.model.LoginInputValidationType
import com.courselara.receitafacil.ui.presentation.features.auth.login.domain.usecase.LoginUserCase
import com.courselara.receitafacil.ui.presentation.features.auth.login.domain.usecase.SaveUserDataUseCase
import com.courselara.receitafacil.ui.presentation.features.auth.login.domain.usecase.ValidateLoginInputUseCase
import com.courselara.receitafacil.ui.presentation.features.auth.login.presentation.state.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUserCase: LoginUserCase,
    private val saveUserDataUseCase: SaveUserDataUseCase,
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
        viewModelScope.launch {
            loginUserCase.invoke(
                parameters = LoginUserCase.Parameters(
                    AuthUserRequestModel(
                        email = _uiState.value.emailValue.trim(),
                        password = _uiState.value.passwordValue.trim()
                    )
                )
            ).observeState(
                onLoading = {
                    _uiState.update {
                        it.copy(isLoading = true)
                    }
                },
                onFailure = { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessageLoginProcess = error.message.toString()
                        )
                    }
                },
                onSuccess = { response ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isSuccessfullyLoggedIn = response.isSuccessFul
                        )
                    }
                    _sideEffectChannel.send(SideEffect.ShowToast(response.message.toString()))
                    saveLocalStorageUserData(
                        response.token.toString(),
                        response.userNamer.toString()
                    )
                }
            )
        }
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


    private suspend fun saveLocalStorageUserData(token: String, userName: String) {
        saveUserDataUseCase.invoke(SaveUserDataUseCase.Parameters(token, userName)).observeState(
            onLoading = {},
            onFailure = {},
            onSuccess = {}
        )

    }

}