package com.courselara.receitafacil.ui.presentation.features.recipes.detail.presentation.state

import com.courselara.receitafacil.core.domain.model.RecipeDetailModel


data class RecipeDetailState (
    val recipeId: String? = null,
    val isLoading: Boolean = false,
    val dialogState: Boolean = false,
    val errorMessage: Boolean? = null,
    val successfullyDeletedRecipe: Boolean = false,
    val recipeDetail: RecipeDetailModel? = null
)


