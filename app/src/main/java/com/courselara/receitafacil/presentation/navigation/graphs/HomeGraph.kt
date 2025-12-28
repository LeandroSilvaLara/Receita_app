package com.courselara.receitafacil.presentation.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.courselara.receitafacil.presentation.navigation.screens.AuthScreens
import com.courselara.receitafacil.presentation.navigation.screens.Graphs
import com.courselara.receitafacil.presentation.navigation.screens.HomeScreens

fun NavGraphBuilder.homeGraph(
    onNavigateUp: () -> Unit
) {
    navigation<Graphs.HomeGraph>(
        startDestination = HomeScreens.HomeScreen
    ){

    }
}