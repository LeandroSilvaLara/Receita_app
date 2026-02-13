package com.courselara.receitafacil.ui.presentation.features.recipes.detail.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.DeleteOutline
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.courselara.receitafacil.R
import com.courselara.receitafacil.core.sideeffects.SideEffect
import com.courselara.receitafacil.core.util.SingleEventEffect
import com.courselara.receitafacil.core.util.extensions.toast
import com.courselara.receitafacil.ui.MainUiState
import com.courselara.receitafacil.ui.presentation.components.dialog.ActionDialog
import com.courselara.receitafacil.ui.presentation.components.topbar.CommonTopBar
import com.courselara.receitafacil.ui.presentation.features.recipes.detail.presentation.componets.RecipeDetailContent
import com.courselara.receitafacil.ui.presentation.features.recipes.detail.presentation.state.RecipeDetailState
import com.courselara.receitafacil.ui.presentation.navigation.NavDestinationHelper
import com.courselara.receitafacil.ui.presentation.navigation.screens.HomeScreens
import com.courselara.receitafacil.ui.theme.ReceitaFacilAppTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun RecipeDetailScreen(
    modifier: Modifier = Modifier,
    uiState: RecipeDetailState,
    sideEffectFlow: Flow<SideEffect>,
    onNavigateUp: () -> Unit,
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
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            CommonTopBar(
                title = stringResource(id = R.string.detail_recipe_text),
                navigationImageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                onNavigationIconButton = { onNavigateUp() },
                action = {
                    IconButton(
                        onClick = { onNavigateToAddUpdateRecipeScreen(uiState.recipeDetail?.id.toString()) }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Edit,
                            contentDescription = "Edit icon",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    IconButton(
                        onClick = { onEvent(RecipeDetailEvent.OnDeleteRecipe(uiState.recipeDetail?.id.toString())) }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.DeleteOutline,
                            contentDescription = "Delete icon",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                }
            )
        },
        content = { paddingValues ->
            RecipeDetailContent(
                paddingValues = paddingValues,
                isLoading = uiState.isLoading,
                errorMessage = uiState.errorMessage,
                recipeDetailModel = uiState.recipeDetail,
            )

        }
    )
    if (uiState.dialogState) {
        ActionDialog(
            title = stringResource(id = R.string.dialog_details_recipe_text),
            description = stringResource(id = R.string.dialog_details_recipe_description_text),
            onDismiss = { onEvent(RecipeDetailEvent.OnDismissDialog) },
            onButtonConfirm = { onEvent(RecipeDetailEvent.OnShowDialog) }
        )
    }

}

@Preview(showBackground = true)
@Composable
private fun RecipeDetailScreenPreview() {
    ReceitaFacilAppTheme {
        RecipeDetailScreen (
            uiState = RecipeDetailState(),
            sideEffectFlow = emptyFlow(),
            onNavigateUp = {},
            onNavigateToRecipesScreen = {},
            onNavigateToAddUpdateRecipeScreen = {},
            onEvent = {}
        )
    }
}