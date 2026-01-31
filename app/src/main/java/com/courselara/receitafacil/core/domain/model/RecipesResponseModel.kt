package com.courselara.receitafacil.core.domain.model

/**
 * Data model representing the response containing recipe information.
 *
 * @property id The unique identifier of the recipe.
 * @property name The name or title of the recipe.
 * @property category The category the recipe belongs to (e.g., Breakfast, Lunch, Dessert).
 * @property ownerName The name of the person who created or owns the recipe. Can be null.
 * @property totalIngredients The total count of ingredients required for the recipe.
 * @property preparationTime The estimated time in minutes required to prepare the recipe.
 */
data class RecipesResponseModel(
    val id: String,
    val name: String,
    val category: String,
    val ownerName: String? =null,
    val totalIngredients: Int,
    val preparationTime: Int
)
