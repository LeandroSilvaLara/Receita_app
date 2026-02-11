package com.courselara.receitafacil.ui.presentation.features.recipes.detail.presentation

sealed class RecipeDetailEvent {
    data object OnShowDialog: RecipeDetailEvent()
    data object OnDismissDialog: RecipeDetailEvent()
    data class OnDeleteRecipe(val recipeId: String): RecipeDetailEvent()
}