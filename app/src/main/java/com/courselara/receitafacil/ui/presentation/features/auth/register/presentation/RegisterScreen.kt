package com.courselara.receitafacil.ui.presentation.features.auth.register.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.courselara.receitafacil.core.sideeffects.SideEffect
import com.courselara.receitafacil.core.util.SingleEventEffect
import com.courselara.receitafacil.core.util.extensions.toast
import com.courselara.receitafacil.ui.presentation.features.auth.register.presentation.components.RegisterContent
import com.courselara.receitafacil.ui.presentation.features.auth.register.presentation.state.RegisterUserState
import com.courselara.receitafacil.ui.presentation.navigation.NavDestinationHelper
import kotlinx.coroutines.flow.Flow

@Composable
fun RegisterScreen(
    uiState: RegisterUserState,
    sideEffectFlow: Flow<SideEffect>,
    onEvent: (RegisterUserEvent) -> Unit,
    onNavigateToLoginScreen: () -> Unit,
    onNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPhoneChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onPasswordRepeatedChanged: (String) -> Unit,
    onToggleVisualTransformationPassword: () -> Unit,
    onToggleVisualTransformationPasswordRepeated: () -> Unit,
) {

    val context = LocalContext.current

    SingleEventEffect(sideEffectFlow) { sideEffect ->
        when (sideEffect) {
            is SideEffect.ShowToast -> context.toast(sideEffect.message)

        }
    }

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
                onRegisterClick = { onEvent(RegisterUserEvent.OnRegisterClick) },
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