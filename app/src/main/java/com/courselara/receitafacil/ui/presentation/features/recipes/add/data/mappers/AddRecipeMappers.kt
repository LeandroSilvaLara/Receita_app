package com.courselara.receitafacil.ui.presentation.features.recipes.add.data.mappers

import com.courselara.receitafacil.core.data.remote.request.AddIngredientsRequest
import com.courselara.receitafacil.core.data.remote.request.AddUpdateRecipeRequest
import com.courselara.receitafacil.core.domain.model.IngredientsModel
import com.courselara.receitafacil.ui.presentation.features.recipes.add.domain.model.AddUpdateRecipeRequestModel

fun IngredientsModel.toAddIngredientsRequestModel(): AddIngredientsRequest {
    return AddIngredientsRequest(name, quantity)
}

fun AddUpdateRecipeRequestModel.toAddUpdateRecipeRequest(): AddUpdateRecipeRequest {
    return AddUpdateRecipeRequest(
        name = name,
        category = category,
        preparationTime = preparationTime,
        preparationMode = preparationModel,
        ingredients = ingredients.map { it.toAddIngredientsRequestModel() }
    )
}