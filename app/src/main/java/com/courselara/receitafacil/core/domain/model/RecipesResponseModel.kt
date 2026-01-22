package com.courselara.receitafacil.core.domain.model

data class RecipesResponseModel(
    val id: String,
    val name: String,
    val category: String,
    val ownerName: String? =null,
    val totalIngredients: Int,
    val preparationTime: Int
)
