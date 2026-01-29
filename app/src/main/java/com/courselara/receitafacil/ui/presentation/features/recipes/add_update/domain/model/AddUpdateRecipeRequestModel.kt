package com.courselara.receitafacil.ui.presentation.features.recipes.add_update.domain.model

import com.courselara.receitafacil.core.domain.model.IngredientsModel

data class AddUpdateRecipeRequestModel(
    val name: String,
    val category: Int,
    val preparationTime: String,
    val preparationModel: String,
    val ingredients: List<IngredientsModel> = emptyList()
)
