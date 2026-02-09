package com.courselara.receitafacil.ui.presentation.features.recipes.detail.presentation.componets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.courselara.receitafacil.core.domain.model.IngredientsModel
import com.courselara.receitafacil.core.domain.model.RecipeDetailModel
import com.courselara.receitafacil.ui.presentation.components.state.ErrorState
import com.courselara.receitafacil.ui.presentation.components.state.LoadingIndicator
import com.courselara.receitafacil.ui.theme.ReceitaFacilAppTheme

@Composable
fun RecipeDetailContent(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    isLoading: Boolean,
    errorMessage: String?,
    recipeDetailModel: RecipeDetailModel?,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(paddingValues)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when {
            isLoading -> {
                LoadingIndicator()
            }

            errorMessage != null -> {
                ErrorState(message = errorMessage)
            }

            recipeDetailModel != null -> {
                RecipeDetailName(
                    modifier = Modifier.padding(start = 8.dp, top = 30.dp),
                    name = recipeDetailModel.name
                )
                RecipeDetailIngredientsAndTime(
                    preparationTime = recipeDetailModel.preparationTime,
                    ingredients = recipeDetailModel.ingredients
                )
                RecipeDetailPreparationMode(
                    preparationMode = recipeDetailModel.preparationModel
                )
            }
            else -> {
                ErrorState(message = "Nenhuma informação disponivel para essa receita")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RecipeDetailContentPreview() {
    ReceitaFacilAppTheme {
        RecipeDetailContent(
            paddingValues = PaddingValues(),
            isLoading = false,
            errorMessage = null,
            recipeDetailModel = RecipeDetailModel(
                id = "1",
                name = "Bolo Caseiro Tradicional",
                preparationModel = LoremIpsum(100).values.first(),
                preparationTime = "90",
                createAt = "10/04/2024",
                category = "Lanche",
                ingredients = listOf(
                    IngredientsModel(id = "1", name = "Farinha de trigo", quantity = "250g"),
                    IngredientsModel(id = "2", name = "Leite", quantity = "200ml"),
                    IngredientsModel(id = "3", name = "Ovos", quantity = "2 unidades"),
                    IngredientsModel(id = "4", name = "Manteiga", quantity = "50g"),
                    IngredientsModel(id = "5", name = "Açúcar", quantity = "100g"),
                    IngredientsModel(
                        id = "6",
                        name = "Fermento em pó",
                        quantity = "1 colher de sopa"
                    )
                )
            )
        )
    }
}
