package com.courselara.receitafacil.ui.presentation.features.auth.login.presentation

sealed class LoginEvent {
    data class OnEmailChange(val email: String) : LoginEvent()
    data class OnPasswordChange(val password: String) : LoginEvent()
    data object OnToggleVisualTransformationPassword : LoginEvent()
    data object OnLoginClick : LoginEvent()
}