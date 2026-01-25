package com.courselara.receitafacil.ui.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.courselara.receitafacil.ui.presentation.features.recipes.list.presentation.ListRecipesScreen
import com.courselara.receitafacil.ui.presentation.navigation.screens.HomeScreens

fun NavGraphBuilder.listRecipesScreen() {

    composable<HomeScreens.ListRecipesScreen> {

        ListRecipesScreen()

    }
}