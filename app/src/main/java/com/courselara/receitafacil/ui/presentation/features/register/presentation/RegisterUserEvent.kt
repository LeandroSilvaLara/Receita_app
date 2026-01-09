package com.courselara.receitafacil.ui.presentation.features.register.presentation

sealed class RegisterUserEvent {
    data object OnRegisterClick : RegisterUserEvent()
}