package com.courselara.receitafacil.ui.presentation.features.register.presentation.state

data class RegisterUserState(
    val nameValue: String = "",
    val emailValue: String = "",
    val phoneValue: String = "",
    val passwordValue: String = "",
    val passwordRepeatedValue: String = "",
    val isInputValid: Boolean = false,
    val isPasswordShow: Boolean = false,
    val isPasswordRepeatedShow: Boolean = false,
    val errorMessageInput: String? = null,
    val isLoading: Boolean = false,
    val isSuccessfullyRegistered: Boolean = false,
    val errorMessageRegisterProcess: String? = null
)
