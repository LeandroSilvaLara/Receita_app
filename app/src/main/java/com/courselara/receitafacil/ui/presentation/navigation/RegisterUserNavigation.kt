package com.courselara.receitafacil.ui.presentation.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.courselara.receitafacil.ui.presentation.features.register.presentation.RegisterScreen
import com.courselara.receitafacil.ui.presentation.features.register.presentation.RegisterUserViewModel
import com.courselara.receitafacil.ui.presentation.navigation.screens.AuthScreens

fun NavGraphBuilder.registerScreen(
    onNavigateToLoginScreen: () -> Unit,
) {
    composable<AuthScreens.RegisterScreen> {

        val viewModel: RegisterUserViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val sideEffectFlow = viewModel.sideEffectChannel

        RegisterScreen(
            uiState = uiState,
            onEvent = {viewModel.onEvent(it)},
            onNameChanged = { viewModel.onNameInputChange(it) },
            onEmailChanged = { viewModel.onEmailInputChange(it) },
            onPhoneChanged = { viewModel.onPhoneNumberInputChange(it) },
            onPasswordChanged = { viewModel.onPasswordInputChange(it) },
            onPasswordRepeatedChanged = { viewModel.onPasswordRepeatedInputChange(it) },
            onToggleVisualTransformationPassword = { viewModel.onToggleVisualTransformationPassword() },
            onToggleVisualTransformationPasswordRepeated = { viewModel.onToggleVisualTransformationPasswordRepeated() },
            onNavigateToLoginScreen = { onNavigateToLoginScreen() },

            )
    }
}

fun NavController.navigateToRegisterScreen() {
    navigate(AuthScreens.RegisterScreen)
}

