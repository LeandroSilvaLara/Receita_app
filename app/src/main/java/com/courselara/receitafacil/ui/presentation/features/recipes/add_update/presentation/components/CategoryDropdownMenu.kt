package com.courselara.receitafacil.ui.presentation.features.recipes.add_update.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.toSize
import com.courselara.receitafacil.R
import com.courselara.receitafacil.ui.theme.ReceitaFacilAppTheme

@Composable
fun CategoryDropdownMenu(
    modifier: Modifier = Modifier,
    selectedText: String,
    categories: Array<String>,
    onTextChange: (String) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    val focusManager = LocalFocusManager.current
    val icon = mirroringDropdownMenu(expanded)

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { onTextChange(it) },
            label = {
                Text(
                    text = stringResource(
                        id = R.string.category_text
                    ),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            readOnly = true,
            trailingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.clickable { expanded = !expanded },
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    expanded = it.isFocused
                }
                .onGloballyPositioned { layoutCoordinates ->
                    textFieldSize = layoutCoordinates.size.toSize()
                }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
                focusManager.clearFocus()
            },
            modifier = Modifier
                .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
                .background(MaterialTheme.colorScheme.background)
        ) {
            categories.forEach { category ->
                DropdownMenuItem(
                    onClick = {
                        onTextChange(category)
                        expanded = false
                        focusManager.clearFocus()
                    },
                    text = {
                        Text(
                            text = category,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    },
                    colors = MenuDefaults.itemColors()
                )
            }
        }
    }

}

fun mirroringDropdownMenu(expanded: Boolean): ImageVector {
    return if (expanded) {
        Icons.Outlined.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryDropdownMenuPreview() {
    ReceitaFacilAppTheme {
        CategoryDropdownMenu(
            selectedText = "Café da manhã",
            categories = arrayOf("Café da manhã", "Almoço", "Sobremesa", "Lanche", "Jantar"),
            onTextChange = {}
        )
    }
}
