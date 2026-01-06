package com.courselara.receitafacil.ui.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.courselara.receitafacil.ui.presentation.navigation.screens.AuthScreens

fun NavGraphBuilder.loginScreen(
    onNavigateToHomeGraphs: () -> Unit,
    onNavigateToRegisterScreen: () -> Unit,
) {
    composable<AuthScreens.LoginScreen> {
        LoginScreen {
            onNavigateToRegisterScreen()
        }
    }
}

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onNavigateToRegisterScreen: () -> Unit
) {
    Box(modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
        ) {
        Button(
            onClick = {
                onNavigateToRegisterScreen()
            }
        ) {
            Text("Login")
        }
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    LoginScreen(onNavigateToRegisterScreen = {})
}