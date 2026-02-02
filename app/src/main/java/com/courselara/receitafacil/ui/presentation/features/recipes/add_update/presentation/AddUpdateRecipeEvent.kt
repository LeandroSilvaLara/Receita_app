package com.courselara.receitafacil.ui.presentation.features.recipes.add_update.presentation

import com.courselara.receitafacil.core.domain.model.IngredientsModel

sealed class AddUpdateRecipeEvent {
    data class OnNameInputChange(val nameValue: String) : AddUpdateRecipeEvent()
    data class OnCategoryInputChange(val nameValue: String) : AddUpdateRecipeEvent()
    data class OnPreparationTimeInputChange(val nameValue: String) : AddUpdateRecipeEvent()
    data class OnPreparationModeInputChange(val nameValue: String) : AddUpdateRecipeEvent()
    data class OnIngredientProductNameInputChange(val nameValue: String) : AddUpdateRecipeEvent()
    data class OnIngredientProductQuantityInputChange(val nameValue: String) : AddUpdateRecipeEvent()
    data class onRemoveIngredient(val ingredientsModel: IngredientsModel) : AddUpdateRecipeEvent()

    object OnAddOrUpdateRecipe : AddUpdateRecipeEvent()
    object OnAddIngredient : AddUpdateRecipeEvent()
}