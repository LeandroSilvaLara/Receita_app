package com.courselara.receitafacil.ui.presentation.features.recipes.search.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.courselara.receitafacil.core.domain.model.RecipesResponseModel
import com.courselara.receitafacil.ui.presentation.components.recipes.RecipesItem

@Composable
fun SearchRecipesContent(
    modifier: Modifier = Modifier,
    results: List<RecipesResponseModel>,
    onNavigateToRecipeDetail: (String) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(
            items = results,
            key = { item ->
                item.id
            }
        ) { recipe ->
            RecipesItem(
                recipe = recipe,
                onNavigateToRecipeDetailScreen = onNavigateToRecipeDetail
            )
        }
    }
}