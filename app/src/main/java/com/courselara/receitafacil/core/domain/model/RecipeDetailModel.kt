package com.courselara.receitafacil.core.domain.model

data class RecipeDetailModel(
    val id: String,
    val name: String,
    val category: String,
    val preparationTime: String,
    val preparationModel: String,
    val createAt: String,
    val ingredients: List<IngredientsModel>
)
