package com.courselara.receitafacil.ui.presentation.features.recipes.list.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.courselara.receitafacil.core.domain.model.RecipesResponseModel
import com.courselara.receitafacil.ui.presentation.components.state.ErrorState
import com.courselara.receitafacil.ui.presentation.components.state.LoadingIndicator
import com.courselara.receitafacil.ui.theme.ReceitaFacilAppTheme

@Composable
fun ListRecipeContent(
    modifier: Modifier = Modifier,
    isEmpty: Boolean,
    isLoading: Boolean,
    errorMessage: String?,
    selectCategory: Int?,
    onSelectedCategory: (Int?) -> Unit,
    recipes: List<RecipesResponseModel>,
    onNavigateToRecipeDetail: (String) -> Unit,
) {
    val categories = rememberCategories()
    Column(
        modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when {
            isLoading -> {
                LoadingIndicator()
            }

            errorMessage != null -> {
                ErrorState(message = errorMessage)
            }

            isEmpty -> {
                CategoryFilterChipList(
                    categories = categories,
                    selectedCategory = selectCategory,
                    onSelectCategory = onSelectedCategory
                )
                RecipesEmptyState(
                    modifier = Modifier.padding(12.dp)
                )
            }

            else -> {
                CategoryFilterChipList(
                    categories = categories,
                    selectedCategory = selectCategory,
                    onSelectCategory = onSelectedCategory
                )
                RecipesItemList(
                    recipes = recipes, onNavigateToRecipeDetailScreen = onNavigateToRecipeDetail
                )
            }
        }
    }
}

@Preview
@Composable
private fun ListRecipeContentPreview() {

    ReceitaFacilAppTheme {
        ListRecipeContent(
            isEmpty = true, isLoading = false, errorMessage = null, recipes = listOf(
            RecipesResponseModel(
                id = "123",
                name = "Lasanha",
                category = "Lanche",
                ownerName = "João",
                totalIngredients = 5,
                preparationTime = 30,
            ),
        ), selectCategory = 3, onSelectedCategory = {}, onNavigateToRecipeDetail = {})
    }
}