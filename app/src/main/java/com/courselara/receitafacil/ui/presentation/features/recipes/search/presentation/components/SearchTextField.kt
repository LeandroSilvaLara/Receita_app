package com.courselara.receitafacil.ui.presentation.features.recipes.search.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.courselara.receitafacil.ui.theme.poppinsFOntFamily

@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    queryTextState: TextFieldState
) {
    Row(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            imageVector = Icons.Outlined.Search,
            contentDescription = "Icon Search",
        )
        BasicTextField(
            modifier = Modifier.weight(1f)
                .padding(12.dp),
            state = queryTextState,
            textStyle = TextStyle(
                fontFamily = poppinsFOntFamily,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            ),
            cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurfaceVariant),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search)
        )
    }
}