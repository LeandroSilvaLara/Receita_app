package com.courselara.receitafacil.ui.presentation.features.recipes.search.presentation.state

import androidx.compose.foundation.text.input.TextFieldState
import com.courselara.receitafacil.core.domain.model.RecipesResponseModel

data class SearchRecipesState (
    val queryTextState: TextFieldState = TextFieldState(),
    val isEmpty: Boolean = false,
    val searchIsEmpty: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val results: List<RecipesResponseModel> = emptyList()
)