package com.courselara.receitafacil.ui.presentation.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.courselara.receitafacil.ui.presentation.features.recipes.list.presentation.RecipesScreen
import com.courselara.receitafacil.ui.presentation.features.recipes.list.presentation.RecipesViewModel
import com.courselara.receitafacil.ui.presentation.navigation.screens.HomeScreens

fun NavGraphBuilder.recipesScreen(
    onNavigateToAuthGraph: () -> Unit = {},
    onNavigationToProfileScreen: () -> Unit,
    onNavigationToSearchScreen: () -> Unit,
    onNavigationToAddRecipeScreen: () -> Unit,
    onNavigationToRecipeDetailScreen: (recipeId: String) -> Unit,
    onNavigationToUsersConnectionScreen: () -> Unit,
) {

    composable<HomeScreens.RecipesScreen> {

        val viewModel: RecipesViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val selectedCategory by viewModel.selectedCategory.collectAsStateWithLifecycle()

        RecipesScreen(
            uiState = uiState,
            selectedCategory = selectedCategory,
            onLogout = {
                viewModel.logout()
                onNavigateToAuthGraph()
            },
            onSelectedCategory = { viewModel.selectedCategory(it) },
            onNavigationToSearchScreen = onNavigationToSearchScreen,
            onNavigationToProfileScreen = onNavigationToProfileScreen,
            onNavigationToUsersConnectionScreen = onNavigationToUsersConnectionScreen,
            onNavigationToRecipeDetailScreen = { onNavigationToRecipeDetailScreen(it) },
            onNavigationToAddRecipeScreen = onNavigationToAddRecipeScreen

        )

    }
}