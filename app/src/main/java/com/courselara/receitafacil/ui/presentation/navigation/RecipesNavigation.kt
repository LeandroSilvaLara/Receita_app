package com.courselara.receitafacil.ui.presentation.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.courselara.receitafacil.ui.presentation.features.recipes.list.presentation.RecipesViewModel
import com.courselara.receitafacil.ui.presentation.navigation.screens.HomeScreens

fun NavGraphBuilder.RecipesScreen() {

    composable<HomeScreens.RecipesScreen> {

        val viewModel: RecipesViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val selectedCategory by viewModel.selectedCategory.collectAsStateWithLifecycle()

        RecipesScreen()

    }
}