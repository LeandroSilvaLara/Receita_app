package com.courselara.receitafacil.ui.presentation.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.courselara.receitafacil.ui.presentation.features.recipes.detail.presentation.RecipeDetailScreen
import com.courselara.receitafacil.ui.presentation.features.recipes.detail.presentation.RecipeDetailViewModel
import com.courselara.receitafacil.ui.presentation.navigation.screens.HomeScreens

fun NavGraphBuilder.recipeDetailScreen (
    onNavigateUp : () -> Unit,
    onNavigateToRecipesScreen: () -> Unit,
    onNavigateToAddUpdateRecipeScreen: (String?) -> Unit,
) {
    composable<HomeScreens.RecipeDetailsScreen> {
        val viewModel: RecipeDetailViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val sideEffectFlow = viewModel.sideEffectChannel

        RecipeDetailScreen(
            uiState = uiState,
            sideEffectFlow = sideEffectFlow,
            onNavigateUp = onNavigateUp,
            onNavigateToRecipesScreen = onNavigateToRecipesScreen,
            onNavigateToAddUpdateRecipeScreen = onNavigateToAddUpdateRecipeScreen,
            onEvent = { viewModel.onEvent(it) }
        )
    }
}

fun NavController.navigateToRecipeDetailScreen(recipeId: String) {
    navigate(HomeScreens.RecipeDetailsScreen(recipeId))
}