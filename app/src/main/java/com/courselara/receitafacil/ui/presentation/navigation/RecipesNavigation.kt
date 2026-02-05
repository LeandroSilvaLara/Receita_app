package com.courselara.receitafacil.ui.presentation.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.courselara.receitafacil.ui.presentation.features.recipes.list.presentation.RecipesScreen
import com.courselara.receitafacil.ui.presentation.features.recipes.list.presentation.RecipesViewModel
import com.courselara.receitafacil.ui.presentation.navigation.screens.HomeScreens

/**
 * Configures the navigation route for the Recipes Screen within the [NavGraphBuilder].
 *
 * This function sets up the composable destination for displaying a list of recipes,
 * managing the [RecipesViewModel] and its UI state, and handling various navigation actions.
 *
 * @param onNavigateToAuthGraph Callback to navigate to the authentication flow, typically triggered after a logout.
 * @param onNavigationToProfileScreen Callback to navigate to the user's profile screen.
 * @param onNavigationToSearchScreen Callback to navigate to the recipe search screen.
 * @param onNavigationToAddRecipeScreen Callback to navigate to the screen for creating a new recipe.
 * @param onNavigationToRecipeDetailScreen Callback to navigate to the details screen of a specific recipe, providing the recipe ID.
 * @param onNavigationToUsersConnectionScreen Callback to navigate to the screen showing user connections or followers.
 */
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

fun NavController.navigateToRecipesScreen() {
    navigate(HomeScreens.RecipesScreen) {
        popUpTo(0)
    }
}