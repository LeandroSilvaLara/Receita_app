package com.courselara.receitafacil.ui.presentation.features.recipes.add_update.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.courselara.receitafacil.R
import com.courselara.receitafacil.core.domain.model.IngredientsModel
import com.courselara.receitafacil.core.sideeffects.SideEffect
import com.courselara.receitafacil.core.util.SingleEventEffect
import com.courselara.receitafacil.core.util.extensions.toast
import com.courselara.receitafacil.ui.presentation.components.topbar.CommonTopBar
import com.courselara.receitafacil.ui.presentation.features.recipes.add_update.presentation.components.AddIngredientDialog
import com.courselara.receitafacil.ui.presentation.features.recipes.add_update.presentation.components.AddUpdateRecipeContent
import com.courselara.receitafacil.ui.presentation.features.recipes.add_update.presentation.state.AddUpdateRecipeUiState
import com.courselara.receitafacil.ui.presentation.navigation.NavDestinationHelper
import kotlinx.coroutines.flow.Flow

@Composable
fun AddUpdateRecipeScreen(
    uiState: AddUpdateRecipeUiState,
    buttonEnabled: Boolean,
    sideEffectFlow: Flow<SideEffect>,
    addIngredientDialogShown: Boolean,
    ingredients: MutableList<IngredientsModel>,
    onDismiss: () -> Unit,
    onOpenDialog: () -> Unit,
    onNavigateUp: () -> Unit,
    onNavigateToListRecipeScreen: () -> Unit,
    onEvent: (AddUpdateRecipeEvent) -> Unit = {},
) {
    val context = LocalContext.current

    SingleEventEffect(sideEffectFlow = sideEffectFlow) { sideEffect ->
        when (sideEffect) {
            is SideEffect.ShowToast -> context.toast(sideEffect.message)
        }
    }

    NavDestinationHelper(
        shouldNavigate = {
            uiState.isOperationSuccessful
        },
        destination = {
            onNavigateToListRecipeScreen()
        }

    )

    Scaffold(
        contentColor = MaterialTheme.colorScheme.background,
        topBar = {
            CommonTopBar(
                title = if (uiState.currentRecipeId.isEmpty()) stringResource(id = R.string.new_recipe_text) else
                    stringResource(id = R.string.update_recipe_text),
                enable = buttonEnabled,
                actionImageVector = Icons.Outlined.Save,
                navigationImageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                onActionIconButton = { onEvent(AddUpdateRecipeEvent.OnAddOrUpdateRecipe) },
                onNavigationIconButton = { onNavigateUp() }
            )
        },
        content = { paddingValues ->
            AddUpdateRecipeContent(
                paddingValues = paddingValues,
                onOpenDialog = onOpenDialog,
                nameValues = uiState.nameInput,
                categoryValues = uiState.categoryInput,
                preparationTimeValues = uiState.preparationTimeInput,
                preparationModeValues = uiState.preparationModeInput,
                errorMessageInput = uiState.errorMessageInput,
                errorMessageRegisterProcess = uiState.errorMessageRegisterProcess,
                ingredients = ingredients,
                isLoading = uiState.isLoading,
                onNameValueChange = { onEvent(AddUpdateRecipeEvent.OnNameInputChange(it)) },
                onCategoryValueChange = { onEvent(AddUpdateRecipeEvent.OnCategoryInputChange(it)) },
                onPreparationTimeValueChange = {
                    onEvent(
                        AddUpdateRecipeEvent.OnPreparationTimeInputChange(
                            it
                        )
                    )
                },
                onPreparationModeValueChange = {
                    onEvent(
                        AddUpdateRecipeEvent.OnPreparationModeInputChange(
                            it
                        )
                    )
                },
                onRemoveIngredient = { onEvent(AddUpdateRecipeEvent.onRemoveIngredient(it)) }
            )
        }
    )
    if (addIngredientDialogShown) {
        AddIngredientDialog(
            onDismiss = { onDismiss() },
            onShowConfirm = { onEvent(AddUpdateRecipeEvent.OnAddIngredient) },
            productNameValue = uiState.ingredientsProductNameInput,
            productQuantityValue = uiState.ingredientsProductQuantityInput,
            errorMessageInput = uiState.errorMessageDialogInput,
            onIngredientProductNameInputChange = {
                onEvent(
                    AddUpdateRecipeEvent.OnIngredientProductNameInputChange(
                        it
                    )
                )
            },
            OnIngredientProductQuantityInputChange = {
                onEvent(
                    AddUpdateRecipeEvent.OnIngredientProductQuantityInputChange(
                        it
                    )
                )
            }
        )
    }
}