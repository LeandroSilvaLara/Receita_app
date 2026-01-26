package com.courselara.receitafacil.ui.presentation.components.recipes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.SupervisedUserCircle
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ChipColors
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.courselara.receitafacil.R
import com.courselara.receitafacil.core.domain.model.RecipesResponseModel
import com.courselara.receitafacil.ui.theme.LightChip
import com.courselara.receitafacil.ui.theme.ReceitaFacilAppTheme
import com.courselara.receitafacil.ui.theme.poppinsFOntFamily

@Composable
fun RecipesItem(
    modifier: Modifier = Modifier,
    recipe: RecipesResponseModel,
    onNavigateToRecipeDetailScreen: (String) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onNavigateToRecipeDetailScreen(recipe.id)
            },
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(CornerSize(12.dp)),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = recipe.name,
                    fontFamily = poppinsFOntFamily,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Timer,
                        contentDescription = null,
                        modifier = Modifier.padding(start = 8.dp, top = 8.dp)
                    )
                    Text(
                        text = "${recipe.preparationTime} ${stringResource(R.string.minutes_text)}",
                        fontFamily = poppinsFOntFamily,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray,
                        modifier = Modifier.padding(top = 8.dp),
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_ingredients),
                        contentDescription = null,
                        modifier = Modifier.padding(start = 8.dp, top = 8.dp)
                    )
                    Text(
                        text = "${recipe.totalIngredients} ${stringResource(R.string.ingredients_text)}",
                        fontFamily = poppinsFOntFamily,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray,
                        modifier = Modifier.padding(top = 8.dp),
                    )
                }
            }
            recipe.ownerName?.let { ownerName ->
                ElevatedAssistChip(
                    modifier = Modifier.padding(start = 8.dp),
                    onClick = { /*TODO*/ },
                    label = {
                        Text(
                            text = ownerName
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.SupervisedUserCircle,
                            contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier.size(AssistChipDefaults.IconSize)
                        )
                    },
                    colors = ChipColors(
                        containerColor = LightChip,
                        labelColor = Color.White,
                        disabledLabelColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        disabledLeadingIconContentColor = Color.Transparent,
                        disabledTrailingIconContentColor = Color.Transparent,
                        leadingIconContentColor = Color.Transparent,
                        trailingIconContentColor = Color.Transparent
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RecipesItemPreview() {
    ReceitaFacilAppTheme {
        RecipesItem(
            modifier = Modifier.fillMaxWidth(),
            recipe = RecipesResponseModel(
                id = "123",
                name = "Lasanha",
                category = "Lanche",
                ownerName = "João",
                totalIngredients = 5,
                preparationTime = 30,
            ),
            onNavigateToRecipeDetailScreen = {}
        )
    }
}