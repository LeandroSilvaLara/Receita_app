package com.courselara.receitafacil.ui.presentation.features.register.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.courselara.receitafacil.ui.presentation.features.register.presentation.components.RegisterContent
import com.courselara.receitafacil.ui.presentation.features.register.presentation.state.RegisterUserState
import com.courselara.receitafacil.ui.presentation.navigation.NavDestinationHelper
import com.courselara.receitafacil.ui.presentation.navigation.screens.AuthScreens

@Composable
fun RegisterScreen(
    uiState: RegisterUserState,
    onRegisterClick: () -> Unit,
    onNavigateToLoginScreen: () -> Unit,
    onNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPhoneChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onPasswordRepeatedChanged: (String) -> Unit,
    onToggleVisualTransformationPassword: () -> Unit,
    onToggleVisualTransformationPasswordRepeated: () -> Unit,
) {

    NavDestinationHelper(
        shouldNavigate = {
            uiState.isSuccessfullyRegistered
        },
        destination = {
            onNavigateToLoginScreen()
        }
    )

    Scaffold(
        content = { paddingValues ->
            RegisterContent(
                paddingValues = paddingValues,
                uiState = uiState,
                onRegisterClick = onRegisterClick,
                onNameChanged = onNameChanged,
                onEmailChanged = onEmailChanged,
                onPhoneChanged = onPhoneChanged,
                onPasswordChanged = onPasswordChanged,
                onPasswordRepeatedChanged = onPasswordRepeatedChanged,
                onToggleVisualTransformationPassword = onToggleVisualTransformationPassword,
                onToggleVisualTransformationPasswordRepeated = onToggleVisualTransformationPasswordRepeated,
                onNavigateToLoginScreen = onNavigateToLoginScreen
            )
        }
    )
}