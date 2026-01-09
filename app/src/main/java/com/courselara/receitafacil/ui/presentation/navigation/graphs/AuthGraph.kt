package com.courselara.receitafacil.ui.presentation.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.courselara.receitafacil.ui.presentation.navigation.loginScreen
import com.courselara.receitafacil.ui.presentation.navigation.registerScreen
import com.courselara.receitafacil.ui.presentation.navigation.screens.AuthScreens
import com.courselara.receitafacil.ui.presentation.navigation.screens.Graphs

fun NavGraphBuilder.authGraph(
    onNavigateToHomeGraphs: () -> Unit,
    onNavigateToLoginScreen: () -> Unit,
    onNavigateToRegisterScreen: () -> Unit,
) {
    navigation<Graphs.AuthGraph>(
        startDestination = AuthScreens.LoginScreen
    ) {
        loginScreen(
            onNavigateToHomeGraphs = onNavigateToHomeGraphs,
            onNavigateToRegisterScreen = onNavigateToRegisterScreen
        )

        registerScreen(
            onNavigateToLoginScreen = onNavigateToLoginScreen
        )
    }


}