package com.courselara.receitafacil.ui.presentation.features.recipes.list.presentation.state

import com.courselara.receitafacil.core.domain.model.RecipesResponseModel

data class RecipesUiState (
    val isEmpty: Boolean = false,
    val isLoading: Boolean = false,
    val userName: String? = null,
    val category: String? = null,
    val errorMessage: String? = null,
    val recipes: List<RecipesResponseModel> = emptyList(),
)