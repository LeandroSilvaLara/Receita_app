package com.courselara.receitafacil.ui.presentation.navigation

import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.courselara.receitafacil.ui.presentation.navigation.graphs.authGraph
import com.courselara.receitafacil.ui.presentation.navigation.graphs.homeGraph
import com.courselara.receitafacil.ui.presentation.navigation.graphs.navigationToHomeGraph
import com.courselara.receitafacil.ui.presentation.navigation.screens.Graphs

/**
 * The primary navigation host for the application, responsible for managing the transitions
 * between the main navigation graphs such as authentication and the home application flow.
 *
 * @param startDestination The initial graph or destination to be displayed when the host is created.
 * @param navController The [NavHostController] used to manage navigation within this host.
 */
@Composable
fun RootHost(
    startDestination: Graphs,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        authGraph(
            onNavigateToHomeGraphs = { navOptions ->
                navController.navigationToHomeGraph(navOptions)
            },
            onNavigateToRegisterScreen = {
                navController.navigateToRegisterScreen()
            },
            onNavigateToLoginScreen = {
                navController.navigateToLoginScreen()
            }
        )
        homeGraph(
            onNavigateUp = {
                navController.navigateUp()
            },
            onNavigateToAuthGraph = {
                navController.navigateToLoginScreen()
            },
            onNavigationToRecipeDetailScreen = {},
            onNavigationToSearchScreen = {},
            onNavigationToAddRecipeScreen = {
                navController.navigateToAddUpdateRecipeScreen(it)
            },
            onNavigationToProfileScreen = {},
            onNavigationToUsersConnectionScreen = {},
            onNavigateToRecipesScreen = {
                navController.navigateToRecipesScreen()
            }
        )
    }
}