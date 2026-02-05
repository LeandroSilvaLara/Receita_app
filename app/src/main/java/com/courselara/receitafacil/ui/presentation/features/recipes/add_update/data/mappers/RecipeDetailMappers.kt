package com.courselara.receitafacil.ui.presentation.features.recipes.add_update.data.mappers

import com.courselara.receitafacil.core.data.remote.responses.IngredientsResponse
import com.courselara.receitafacil.core.data.remote.responses.RecipeDetailsResponse
import com.courselara.receitafacil.core.domain.model.IngredientsModel
import com.courselara.receitafacil.core.domain.model.RecipeDetailModel

fun IngredientsResponse.toRecipeDetailModel(): IngredientsModel {
    return IngredientsModel(
        id,
        name,
        category
    )
}

fun RecipeDetailsResponse.toRecipeDetailModel(): RecipeDetailModel {
    return RecipeDetailModel(
        id = id,
        name = name,
        category = category,
        preparationTime = preparationTime,
        preparationModel = preparationMode,
        createAt = createAt,
        ingredients = ingredients.map { it.toRecipeDetailModel() }
    )
}