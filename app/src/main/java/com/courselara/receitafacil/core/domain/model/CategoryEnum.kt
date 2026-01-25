package com.courselara.receitafacil.core.domain.model

import androidx.annotation.DrawableRes
import com.courselara.receitafacil.R

enum class CategoryEnum(
    val value: Int,
    val description: String,
    @DrawableRes val iconRes: Int
) {
    Breakfast(0, "Café da manhã", R.drawable.ic_free_breakfast),
    Lunch(1, "Almoço", R.drawable.ic_restaurant),
    Dessert(2, "Sobremesa", R.drawable.ic_icecream),
    Snack(3, "Lanche", R.drawable.ic_fastfood),
    Dinner(4, "Jantar", R.drawable.ic_dinner_dining);

    companion object {
        fun fromInt(value: Int) = entries.firstOrNull{ it.value == value }
        fun fromDescription(description: String) =
            entries.firstOrNull{ it.description == description }

    }
}