package com.courselara.receitafacil.ui.presentation.features.recipes.add_update.presentation.state

data class AddUpdateRecipeUiState(
    val currentRecipeId: String = "",
    val nameInput: String = "",
    val categoryInput: String = "",
    val preparationTimeInput: String = "",
    val preparationModeInput: String = "",
    val ingredientsInput: String = "",
    val ingredientsProductNameInput: String = "",
    val ingredientsProductQuantityInput: String = "",
    val isLoading: Boolean = false,
    val isInputValid: Boolean = false,
    val isInputDialogValid: Boolean = false,
    val errorMessage: String? = null,
    val errorMessageInput: String? = null,
    val errorMessageDialogInput: String? = null,
    val isOperationSuccessful: Boolean = false,
    val errorMessageRegisterProcess: String? = null
)
