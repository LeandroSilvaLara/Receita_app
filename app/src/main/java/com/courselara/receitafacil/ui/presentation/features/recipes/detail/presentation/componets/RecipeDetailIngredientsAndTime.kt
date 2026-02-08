package com.courselara.receitafacil.ui.presentation.features.recipes.detail.presentation.componets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.courselara.receitafacil.R
import com.courselara.receitafacil.core.domain.model.IngredientsModel
import com.courselara.receitafacil.ui.presentation.components.ingretients.IngredientItem
import com.courselara.receitafacil.ui.theme.ReceitaFacilAppTheme
import com.courselara.receitafacil.ui.theme.poppinsFOntFamily

@Composable
fun RecipeDetailIngredientsAndTime(
    modifier: Modifier = Modifier,
    preparationTime: String,
    ingredients: List<IngredientsModel>
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Icon(
                imageVector = Icons.Outlined.Timer,
                contentDescription = "Timer icon",
                modifier = Modifier.padding(start = 4.dp)
            )
            Text(
                text = preparationTime,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
                fontFamily = poppinsFOntFamily
            )
            Text(
                text = stringResource(id = R.string.minutes_recipe_text),
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodyMedium,
                fontFamily = poppinsFOntFamily,
                fontWeight = FontWeight.Bold,
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_ingredients),
                contentDescription = "ingredients icon",
                modifier = Modifier.padding(start = 4.dp)
            )
            Text(
                text = ingredients.size.toString(),
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyMedium,
                fontFamily = poppinsFOntFamily
            )
            Text(
                text = stringResource(id = R.string.ingredients_text),
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodyMedium,
                fontFamily = poppinsFOntFamily,
                fontWeight = FontWeight.Bold,
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = stringResource(id = R.string.ingredients_text),
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodyMedium,
                fontFamily = poppinsFOntFamily,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
        ingredients.forEach { ingredients ->
            IngredientItem(
                productName = ingredients.name,
                productQuantity = ingredients.quantity
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun RecipeDetailIngredientsAndTimePreview() {
    ReceitaFacilAppTheme {
        RecipeDetailIngredientsAndTime(
            preparationTime = "120",
            ingredients = listOf(
                IngredientsModel(id = "1", name = "Farinha de trigo", quantity = "250g"),
                IngredientsModel(id = "2", name = "Leite", quantity = "200ml"),
                IngredientsModel(id = "3", name = "Ovos", quantity = "2 unidades"),
                IngredientsModel(id = "4", name = "Manteiga", quantity = "50g"),
                IngredientsModel(id = "5", name = "Açúcar", quantity = "100g"),
                IngredientsModel(id = "6", name = "Fermento em pó", quantity = "1 colher de sopa")
            )
        )
    }
}
