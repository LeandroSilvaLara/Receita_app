package com.courselara.receitafacil.ui.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.courselara.receitafacil.ui.presentation.navigation.screens.HomeScreens

fun NavGraphBuilder.addUpdateRecipeScreen(
    onNavigateUp: () -> Unit,
    onNavigateToListRecipeScreen: () -> Unit
) {
    composable<HomeScreens.AddRecipeScreen> {

    }
}