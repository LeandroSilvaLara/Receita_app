package com.courselara.receitafacil.ui.presentation.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import com.courselara.receitafacil.ui.presentation.navigation.recipesScreen
import com.courselara.receitafacil.ui.presentation.navigation.screens.Graphs
import com.courselara.receitafacil.ui.presentation.navigation.screens.HomeScreens

fun NavGraphBuilder.homeGraph(
    onNavigateUp: () -> Unit,
    onNavigateToAuthGraph: () -> Unit = {},
    onNavigationToProfileScreen: () -> Unit,
    onNavigationToSearchScreen: () -> Unit,
    onNavigationToAddRecipeScreen: () -> Unit,
    onNavigationToRecipeDetailScreen: (recipeId: String) -> Unit,
    onNavigationToUsersConnectionScreen: () -> Unit,
) {
    navigation<Graphs.HomeGraph>(
        startDestination = HomeScreens.RecipesScreen
    ) {
        recipesScreen(
            onNavigateToAuthGraph = onNavigateToAuthGraph,
            onNavigationToProfileScreen = onNavigationToProfileScreen,
            onNavigationToSearchScreen = onNavigationToSearchScreen,
            onNavigationToAddRecipeScreen = onNavigationToAddRecipeScreen,
            onNavigationToRecipeDetailScreen = { onNavigationToRecipeDetailScreen(it) },
            onNavigationToUsersConnectionScreen = onNavigationToUsersConnectionScreen
        )
    }
}

fun NavController.navigationToHomeGraph(
    navOptions: NavOptions? = null
) {
    navigate(Graphs.HomeGraph, navOptions)
}

