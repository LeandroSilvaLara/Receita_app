package com.courselara.receitafacil.presentation.navigation

import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.courselara.receitafacil.presentation.navigation.graphs.authGraph
import com.courselara.receitafacil.presentation.navigation.graphs.homeGraph
import com.courselara.receitafacil.presentation.navigation.screens.Graphs

@Composable
fun RootHost(
    startDestination: Graphs,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        authGraph(
            onNavigateToHomeGraphs = {
                navController.navigate(Graphs.HomeGraph)
            }
        )
        homeGraph(
            onNavigateUp = {
                navController.navigateUp()
            }
        )
    }
}