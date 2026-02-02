package com.courselara.receitafacil.ui.presentation.features.recipes.add_update.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.EmojiFoodBeverage
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.courselara.receitafacil.R
import com.courselara.receitafacil.core.domain.model.IngredientsModel
import com.courselara.receitafacil.ui.presentation.components.state.ErrorState
import com.courselara.receitafacil.ui.presentation.components.state.LoadingIndicator
import com.courselara.receitafacil.ui.presentation.components.textfield.TextEntryModule
import com.courselara.receitafacil.ui.theme.ReceitaFacilAppTheme
import com.courselara.receitafacil.ui.theme.poppinsFOntFamily

@Composable
fun AddUpdateRecipeContent(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    nameValues: String,
    preparationModeValues: String,
    preparationTimeValues: String,
    categoryValues: String,
    ingredients: MutableList<IngredientsModel>,
    isLoading: Boolean,
    errorMessageInput: String?,
    errorMessageRegisterProcess: String?,
    onNameValueChange: (String) -> Unit,
    onCategoryValueChange: (String) -> Unit,
    onPreparationTimeValueChange: (String) -> Unit,
    onPreparationModeValueChange: (String) -> Unit,
    onOpenDialog: () -> Unit,
    onRemoveIngredient: (IngredientsModel) -> Unit,
) {
    when{
        isLoading -> {
            LoadingIndicator(

            )
        }
        errorMessageRegisterProcess != null -> {
            ErrorState(message = errorMessageRegisterProcess)
        }

        else -> {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (!errorMessageInput.isNullOrEmpty()) {
                    Text(
                        text = errorMessageInput,
                        maxLines = 2,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall,
                        overflow = TextOverflow.Ellipsis,
                        fontFamily = poppinsFOntFamily,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp)
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    TextEntryModule(
                        description = stringResource(id = R.string.description_name_recipe_text),
                        hint = stringResource(id = R.string.hint_name_recipe_text),
                        leadingIcon = Icons.Outlined.EmojiFoodBeverage,
                        textValue = nameValues,
                        textColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        cursorColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        onValueChange = onNameValueChange
                    )
                    TextEntryModule(
                        description = stringResource(id = R.string.description_time_preparation_text),
                        hint = stringResource(id = R.string.hint_time_preparation_text),
                        leadingIcon = Icons.Outlined.Timer,
                        textValue = preparationTimeValues,
                        textColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        cursorColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        keyboardType = KeyboardType.Number,
                        onValueChange = onPreparationTimeValueChange
                    )
                    CategoryDropdownMenu(
                      selectedText = categoryValues,
                        categories = stringArrayResource(id = R.array.categories),
                        onTextChange = onCategoryValueChange
                    )
                    TextEntryModule(
                        description = stringResource(id = R.string.description_mode_preparation_text),
                        hint = stringResource(id = R.string.hint_mode_preparation_text),
                        leadingIcon = Icons.Outlined.Description,
                        textValue = preparationModeValues,
                        textColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        cursorColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        onValueChange = onPreparationModeValueChange,
                        modifier = Modifier.heightIn(min = 200.dp)
                    )
                    IngredientItemList(
                      onOpenDialog = onOpenDialog,
                        ingredients = ingredients,
                        onRemoveIngredient = onRemoveIngredient
                    )
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun AddUpdateRecipeContentPreview() {
    ReceitaFacilAppTheme {
        AddUpdateRecipeContent(
            paddingValues = PaddingValues(8.dp),
            nameValues = "Bolo de Chocolate",
            isLoading = false,
            errorMessageInput = null,
            errorMessageRegisterProcess = null,
            ingredients = mutableListOf(
                IngredientsModel("1", "Farinha", "2 xícaras"),
                IngredientsModel("2", "Ovos", "3 unidades"),
                IngredientsModel("3", "Açúcar", "1 xícara")
            ),
            categoryValues = "Sobremesa",
            preparationTimeValues = "45 minutos",
            preparationModeValues = "Misture os ingredientes e asse por 40 minutos a 180ºC.",
            onNameValueChange = {},
            onCategoryValueChange = {},
            onPreparationTimeValueChange = {},
            onPreparationModeValueChange = {},
            onOpenDialog = {},
            onRemoveIngredient = {}
        )
    }
    
}