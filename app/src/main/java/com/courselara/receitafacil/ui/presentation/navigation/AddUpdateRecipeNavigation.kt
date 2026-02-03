package com.courselara.receitafacil.ui.presentation.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.courselara.receitafacil.ui.presentation.features.recipes.add_update.presentation.AddUpdateRecipeScreen
import com.courselara.receitafacil.ui.presentation.features.recipes.add_update.presentation.AddUpdateRecipeViewModel
import com.courselara.receitafacil.ui.presentation.navigation.screens.HomeScreens

fun NavGraphBuilder.addUpdateRecipeScreen(
    onNavigateUp: () -> Unit,
    onNavigateToRecipesScreen: () -> Unit
) {
    composable<HomeScreens.AddRecipeScreen> {

        val viewModel: AddUpdateRecipeViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        val sideEffectFlow = viewModel.sideEffectChannel

        val addIngredientDialogShown = viewModel.isAddIngredientDialogShown
        val ingredients = viewModel.ingredients
        val buttonEnabled = uiState.isInputValid && ingredients.isNotEmpty()

        AddUpdateRecipeScreen(
            uiState = uiState,
            sideEffectFlow = sideEffectFlow,
            ingredients = ingredients,
            buttonEnabled = buttonEnabled,
            addIngredientDialogShown = addIngredientDialogShown,
            onDismiss = {viewModel.onDismissDialog()},
            onOpenDialog = {viewModel.onOpenDialog()},
            onNavigateUp = onNavigateUp,
            onNavigateToRecipesScreen = onNavigateToRecipesScreen
        )
    }
}

fun NavController.navigateToAddUpdateRecipeScreen(recipeId: String? = null) {
    navigate(HomeScreens.AddRecipeScreen(recipeId))
}