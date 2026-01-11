package com.courselara.receitafacil.ui.presentation.features.auth.login.presentation.state

data class LoginUiState (
    val emailValue: String = "",
    val passwordValue: String = "",
    val isLoading: Boolean = false,
    val isInputValid: Boolean = false,
    val isPasswordShow: Boolean = false,
    val errorMessageInput: String? = null,
    val isSuccessfullyLoggedIn: Boolean = false,
    val errorMessageLoginProcess: String? = null
)
