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

/**
 * Adds the Add/Update Recipe screen to the [NavGraphBuilder].
 *
 * This function defines the composable destination for creating or editing a recipe.
 * It manages the [AddUpdateRecipeViewModel] lifecycle, collects UI state, and passes
 * the necessary state and callbacks to the [AddUpdateRecipeScreen].
 *
 * @param onNavigateUp Callback to be invoked when the user requests to navigate back.
 * @param onNavigateToRecipesScreen Callback to be invoked after a successful recipe
 * operation to return to the main recipes list.
 */
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