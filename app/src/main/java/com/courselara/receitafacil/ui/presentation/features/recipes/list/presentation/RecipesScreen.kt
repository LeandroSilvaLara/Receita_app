package com.courselara.receitafacil.ui.presentation.features.recipes.list.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.courselara.receitafacil.ui.presentation.components.bottombar.BottomBar
import com.courselara.receitafacil.ui.presentation.components.topbar.CommonTopBar
import com.courselara.receitafacil.ui.presentation.features.recipes.list.presentation.components.RecipesContent
import com.courselara.receitafacil.ui.presentation.features.recipes.list.presentation.state.RecipesUiState

@Composable
fun RecipesScreen(
    uiState: RecipesUiState,
    selectedCategory: Int?,
    onLogout: () -> Unit,
    onSelectedCategory: (Int?) -> Unit,
    onNavigationToProfileScreen: () -> Unit,
    onNavigationToSearchScreen: () -> Unit,
    onNavigationToAddRecipeScreen: () -> Unit,
    onNavigationToRecipeDetailScreen: (recipeId: String) -> Unit,
    onNavigationToUsersConnectionScreen: () -> Unit,
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            CommonTopBar(
                title = "Ola, ${uiState.userName}",
                actionImageVector = Icons.AutoMirrored.Outlined.Logout,
                onActionIconButton = { onLogout() }
            )
        },
        bottomBar = {
            BottomBar(
                onNavigationToAddRecipeScreen = onNavigationToAddRecipeScreen,
                onNavigateToSearchScreen = onNavigationToSearchScreen,
                onNavigateToProfileScreens = onNavigationToProfileScreen,
                onNavigateToUsersConnectionScreen = onNavigationToUsersConnectionScreen
            )
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                RecipesContent(
                    selectCategory = selectedCategory,
                    onSelectedCategory = { onSelectedCategory ->
                        onSelectedCategory(onSelectedCategory)
                    },
                    isEmpty = uiState.isEmpty,
                    isLoading = uiState.isLoading,
                    errorMessage = uiState.errorMessage,
                    recipes = uiState.recipes,
                    onNavigateToRecipeDetail = onNavigationToRecipeDetailScreen
                )
            }
        }
    )
}