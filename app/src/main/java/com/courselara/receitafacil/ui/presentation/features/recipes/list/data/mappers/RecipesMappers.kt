package com.courselara.receitafacil.ui.presentation.features.recipes.list.data.mappers

import com.courselara.receitafacil.core.data.remote.responses.RecipesResponse
import com.courselara.receitafacil.core.domain.model.RecipesResponseModel

fun RecipesResponse.toRecipesResponseModel(): RecipesResponseModel {
    return RecipesResponseModel(id, name, category, ownerName, totalIngredients, preparationTime)
}