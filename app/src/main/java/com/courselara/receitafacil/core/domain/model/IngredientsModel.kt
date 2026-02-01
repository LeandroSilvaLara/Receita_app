package com.courselara.receitafacil.core.domain.model

/**
 * Represents an ingredient within a recipe, containing its identification, name, and amount.
 *
 * @property id The unique identifier for the ingredient, typically used for database mapping.
 * @property name The descriptive name of the ingredient (e.g., "Sugar", "Eggs").
 * @property quantity The amount required, including units (e.g., "2 cups", "500g").
 */
data class IngredientsModel(
    val id: String ,
    val name: String,
    val quantity: String,
)
