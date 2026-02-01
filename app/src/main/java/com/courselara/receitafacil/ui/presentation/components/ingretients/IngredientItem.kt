package com.courselara.receitafacil.ui.presentation.components.ingretients

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material.icons.outlined.RemoveCircleOutline
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.courselara.receitafacil.ui.theme.ReceitaFacilAppTheme
import com.courselara.receitafacil.ui.theme.poppinsFOntFamily

@Composable
fun IngredientItem(
    modifier: Modifier = Modifier,
    productName: String,
    productQuantity: String,
    iconButton: Boolean = false,
    onRemoveIngredient: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Outlined.Circle,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(12.dp)
        )
        Text(
            text = productQuantity,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.ExtraBold,
            style = MaterialTheme.typography.bodyMedium,
            fontFamily = poppinsFOntFamily
        )
        Text(
            text = productName,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyMedium,
            fontFamily = poppinsFOntFamily
        )
        if (iconButton) {
            Spacer(Modifier.weight(1f))
            IconButton(
                onClick = onRemoveIngredient
            ) {
                Icon(
                    imageVector = Icons.Outlined.RemoveCircleOutline,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
    HorizontalDivider(color = Color.DarkGray)
}

@Preview(showBackground = true)
@Composable
private fun IngredientItemPreview() {
    ReceitaFacilAppTheme {
        IngredientItem(
            productName = "Product Name",
            productQuantity = "500g",
            iconButton = true,
            onRemoveIngredient = {}
        )
    }
}