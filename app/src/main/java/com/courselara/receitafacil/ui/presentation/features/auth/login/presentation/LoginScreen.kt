package com.courselara.receitafacil.ui.presentation.features.auth.login.presentation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.courselara.receitafacil.core.sideeffects.SideEffect
import com.courselara.receitafacil.core.util.SingleEventEffect
import com.courselara.receitafacil.core.util.extensions.toast
import com.courselara.receitafacil.ui.presentation.features.auth.login.presentation.components.LoginContent
import com.courselara.receitafacil.ui.presentation.features.auth.login.presentation.state.LoginUiState
import com.courselara.receitafacil.ui.presentation.navigation.NavDestinationHelper
import kotlinx.coroutines.flow.Flow

@Composable
fun LoginScreen(
    uiState: LoginUiState,
    sideEffectFlow: Flow<SideEffect>,
    onEvent: (LoginEvent) -> Unit,
    onNavigateToRegisterScreen: () -> Unit,
    onNavigateToHome: () -> Unit,
) {

    val context = LocalContext.current

    SingleEventEffect(sideEffectFlow = sideEffectFlow) { sideEffect ->
        when (sideEffect) {
            is SideEffect.ShowToast -> context.toast(sideEffect.message)
        }
    }

    NavDestinationHelper(
        shouldNavigate = {
            uiState.isSuccessfullyLoggedIn
        },
        destination = {
            onNavigateToHome()
        }
    )

    Scaffold(
        content = { paddingValues ->
            LoginContent(
                paddingValues = paddingValues,
                uiState = uiState,
                onLoginClick = { onEvent(LoginEvent.OnLoginClick) },
                onEmailChange = { onEvent(LoginEvent.OnEmailChange(it)) },
                onPasswordChange = { onEvent(LoginEvent.OnPasswordChange(it)) },
                onToggleVisualTransformationPassword = { onEvent(LoginEvent.OnToggleVisualTransformationPassword) },
                onNavigatorToRegisterScreens = onNavigateToRegisterScreen
            )
        }
    )
}