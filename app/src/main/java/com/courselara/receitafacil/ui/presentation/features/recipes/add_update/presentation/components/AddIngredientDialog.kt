package com.courselara.receitafacil.ui.presentation.features.recipes.add_update.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cookie
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.courselara.receitafacil.R
import com.courselara.receitafacil.ui.presentation.components.textfield.TextEntryModule
import com.courselara.receitafacil.ui.theme.ReceitaFacilAppTheme
import com.courselara.receitafacil.ui.theme.poppinsFOntFamily

@Composable
fun AddIngredientDialog(
    modifier: Modifier = Modifier,
    productNameValue: String,
    productQuantityValue: String,
    errorMessageInput: String?,
    onDismiss: () -> Unit,
    onShowConfirm: () -> Unit,
    onIngredientProductNameInputChange: (String) -> Unit,
    OnIngredientProductQuantityInputChange: (String) -> Unit,
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp),
            elevation = CardDefaults.cardElevation(),
            shape = RoundedCornerShape(CornerSize(8.dp)),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = errorMessageInput ?: "",
                    maxLines = 2,
                    fontFamily = poppinsFOntFamily,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                )
                Column {
                    TextEntryModule(
                        description = stringResource(id = R.string.description_product_text),
                        hint = stringResource(id = R.string.hint_product_text),
                        leadingIcon = Icons.Outlined.Cookie,
                        textValue = productNameValue,
                        onValueChange = {onIngredientProductNameInputChange(it)},
                        textColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        cursorColor = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    TextEntryModule(
                        description = stringResource(id = R.string.description_amount_text),
                        hint = stringResource(id = R.string.hint_amount_text),
                        leadingIcon = Icons.Outlined.Description,
                        textValue = productQuantityValue,
                        textColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        cursorColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        onValueChange = {OnIngredientProductQuantityInputChange(it)},
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = { onDismiss() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        Text(
                            text = stringResource(id = R.string.close_text),
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            fontFamily = poppinsFOntFamily,
                            color = Color.White,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                    Button(
                        onClick = { onShowConfirm() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        Text(
                            text = stringResource(id = R.string.save_text),
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            fontFamily = poppinsFOntFamily,
                            color = Color.White,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }

            }
        }
    }
}

@Preview
@Composable
private fun AddIngredientDialogPreview() {
    ReceitaFacilAppTheme {
        AddIngredientDialog(
            onDismiss = {},
            onShowConfirm = {},
            productNameValue = "",
            productQuantityValue = "",
            errorMessageInput = "",
            onIngredientProductNameInputChange = {},
            OnIngredientProductQuantityInputChange = {}

        )
    }
}