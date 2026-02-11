package com.courselara.receitafacil.ui.presentation.features.recipes.detail.presentation

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.courselara.receitafacil.core.sideeffects.SideEffect
import com.courselara.receitafacil.core.util.SingleEventEffect
import com.courselara.receitafacil.core.util.extensions.toast
import com.courselara.receitafacil.ui.MainUiState
import com.courselara.receitafacil.ui.presentation.components.topbar.CommonTopBar
import com.courselara.receitafacil.ui.presentation.features.recipes.detail.presentation.state.RecipeDetailState
import com.courselara.receitafacil.ui.presentation.navigation.NavDestinationHelper
import com.courselara.receitafacil.ui.presentation.navigation.screens.HomeScreens
import kotlinx.coroutines.flow.Flow

@Composable
fun RecipeDetailScreen(
    modifier: Modifier = Modifier,
    uiState: RecipeDetailState,
    sideEffectFlow: Flow<SideEffect>,
    onNavigateToRecipesScreen: () -> Unit,
    onNavigateToAddUpdateRecipeScreen: (String?) -> Unit,
    onEvent: (RecipeDetailEvent) -> Unit
) {
    val context = LocalContext.current

    SingleEventEffect(sideEffectFlow = sideEffectFlow) { sideEffectFlow ->
        when (sideEffectFlow) {
            is SideEffect.ShowToast -> context.toast(sideEffectFlow.message)
        }
    }

    NavDestinationHelper(
        shouldNavigate = { uiState.successfullyDeletedRecipe },
        destination = { onNavigateToRecipesScreen() }
    )

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            CommonTopBar()
        }
    )

}