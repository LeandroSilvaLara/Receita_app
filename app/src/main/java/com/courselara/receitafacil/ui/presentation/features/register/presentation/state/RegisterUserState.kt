package com.courselara.receitafacil.ui.presentation.features.register.presentation.state

data class RegisterUserState(
    val nameInput: String = "",
    val emailInput: String = "",
    val phoneNumberInput: String = "",
    val passwordInput: String = "",
    val passwordRepeatedInput: String = "",
    val isInputValid: Boolean = false,
    val isPasswordShow: Boolean = false,
    val isPasswordRepeatedShow: Boolean = false,
    val errorMessageInput: String? = null,
    val isLoading: Boolean = false,
    val isSuccessfullyRegistered: Boolean = false,
    val errorMessageRegisterProcess: String? = null
)
