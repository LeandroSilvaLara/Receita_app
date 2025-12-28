package com.courselara.receitafacil.presentation.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.courselara.receitafacil.presentation.navigation.screens.AuthScreens
import com.courselara.receitafacil.presentation.navigation.screens.Graphs

fun NavGraphBuilder.authGraph(
    onNavigateToHomeGraphs: () -> Unit
) {
    navigation<Graphs.AuthGraph>(
        startDestination = AuthScreens.LoginScreen
    ){

    }
}