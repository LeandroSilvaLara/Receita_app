package com.courselara.receitafacil.ui.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.courselara.receitafacil.ui.presentation.features.auth.login.presentation.LoginScreen
import com.courselara.receitafacil.ui.presentation.features.auth.login.presentation.LoginViewModel
import com.courselara.receitafacil.ui.presentation.navigation.screens.AuthScreens

/**
 * Extension function on [NavGraphBuilder] to define the login screen destination in the navigation graph.
 *
 * This function sets up the [LoginScreen] composable, providing it with its corresponding
 * [LoginViewModel] and handling navigation actions.
 *
 * @param onNavigateToHomeGraphs Callback invoked when the user successfully authenticates and should be redirected to the home graph.
 * @param onNavigateToRegisterScreen Callback invoked when the user requests to navigate to the registration screen.
 */
fun NavGraphBuilder.loginScreen(
    onNavigateToHomeGraphs: () -> Unit,
    onNavigateToRegisterScreen: () -> Unit,
) {
    composable<AuthScreens.LoginScreen> {

        val viewModel: LoginViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val sideEffectFlow = viewModel.sideEffectChannel



        LoginScreen (
            uiState = uiState,
            sideEffectFlow = sideEffectFlow,
            onEvent = { viewModel.onEvent(it)} ,
            onNavigateToRegisterScreen = onNavigateToHomeGraphs,
            onNavigateToHome = onNavigateToRegisterScreen,
        )

    }
}

fun NavController.navigateToLoginScreen() {
    navigate(AuthScreens.LoginScreen) {
        popUpTo(0)
    }
}



@Preview
@Composable
private fun LoginScreenPreview() {
    //LoginScreen(onNavigateToRegisterScreen = {})
}