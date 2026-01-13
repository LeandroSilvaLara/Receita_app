package com.courselara.receitafacil.ui.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.courselara.receitafacil.ui.presentation.features.auth.login.presentation.LoginScreen
import com.courselara.receitafacil.ui.presentation.navigation.screens.AuthScreens

fun NavGraphBuilder.loginScreen(
    onNavigateToHomeGraphs: () -> Unit,
    onNavigateToRegisterScreen: () -> Unit,
) {
    composable<AuthScreens.LoginScreen> {


        LoginScreen (
            uiState = ,
            sideEffectFlow = ,
            onEvent = {} ,
            onNavigateToRegisterScreen = { },
            onNavigateToHome = { },
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