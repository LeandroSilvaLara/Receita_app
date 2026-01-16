package com.courselara.receitafacil.ui.presentation.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navOptions
import androidx.navigation.navigation
import com.courselara.receitafacil.ui.presentation.navigation.loginScreen
import com.courselara.receitafacil.ui.presentation.navigation.registerScreen
import com.courselara.receitafacil.ui.presentation.navigation.screens.AuthScreens
import com.courselara.receitafacil.ui.presentation.navigation.screens.Graphs

/**
 * Configures the authentication navigation graph.
 *
 * This graph handles the authentication flow, including the login and registration screens.
 *
 * @param onNavigateToHomeGraphs Callback to navigate to the main application graphs upon successful authentication.
 * @param onNavigateToLoginScreen Callback to navigate to the login screen.
 * @param onNavigateToRegisterScreen Callback to navigate to the registration screen.
 */
fun NavGraphBuilder.authGraph(
    onNavigateToHomeGraphs: (NavOptions) -> Unit,
    onNavigateToLoginScreen: () -> Unit,
    onNavigateToRegisterScreen: () -> Unit,
) {
    navigation<Graphs.AuthGraph>(
        startDestination = AuthScreens.LoginScreen
    ) {
        loginScreen(
            onNavigateToHomeGraphs = {
                onNavigateToHomeGraphs(
                    navOptions {
                        popUpTo(Graphs.AuthGraph)

                        })
                    },
                    onNavigateToRegisterScreen = onNavigateToRegisterScreen
                )

                registerScreen(
                    onNavigateToLoginScreen = onNavigateToLoginScreen
                )
            }

    }