package com.courselara.receitafacil.presentation.navigation.screens

import kotlinx.serialization.Serializable


@Serializable
sealed class Graphs {

    @Serializable
    data object AuthGraph : Graphs()

    @Serializable
    data object HomeGraph : Graphs()

}

@Serializable
sealed class AuthScreens {
    @Serializable
    data object LoginScreen : AuthScreens()

    @Serializable
    data object RegisterScreen : AuthScreens()
}

@Serializable
sealed class HomeScreens {
    @Serializable
    data object HomeScreen : HomeScreens()

    @Serializable
    data class AddRecipeScreen(val recipeId: String?= "") : HomeScreens()

    @Serializable
    data object ProfileScreen : HomeScreens()

    @Serializable
    data object CreateQrCodeScreen : HomeScreens()

    @Serializable
    data object ReadQrCodeScreen : HomeScreens()

    @Serializable
    data object UsersConnectionsScreen : HomeScreens()

    @Serializable
    data object SearchScreenScreen : HomeScreens()

}

