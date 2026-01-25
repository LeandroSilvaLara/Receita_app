package com.courselara.receitafacil.ui.presentation.features.recipes.list.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.courselara.receitafacil.core.data.remote.responses.RecipesResponse
import com.courselara.receitafacil.core.domain.model.RecipesResponseModel
import com.courselara.receitafacil.ui.presentation.components.recipes.RecipesItem

@Composable
fun RecipesItemList(
    modifier: Modifier = Modifier,
    recipesResponseModel: List<RecipesResponseModel>,
    onNavigateToRecipeDetailScreen: (String) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(8.dp),
        content = {
            items(100) {
                RecipesItem(
                    recipes = recipesResponseModel.first(),
                    onNavigateToRecipeDetailScreen = onNavigateToRecipeDetailScreen
                )
            }
        }
    )
}

@Preview
@Composable
private fun RecipesItemListPreview() {
    RecipesItemList(
      recipesResponseModel = listOf(
          RecipesResponseModel(
              id = "1",
              name = "Cachorro quente",
              category = "Lanche",
              ownerName = "João",
              totalIngredients = 5,
              preparationTime = 30,
          ),
      ),
        onNavigateToRecipeDetailScreen = {}
    )
}