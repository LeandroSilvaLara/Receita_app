package com.courselara.receitafacil.ui.presentation.features.recipes.search.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SearchRecipeBar(
    modifier: Modifier = Modifier,
    queryTextState: TextFieldState,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SearchTextField(
            queryTextState = queryTextState,
            modifier = Modifier.weight(1f)
        )
        ClearButton(
            isVisible = queryTextState.text.isNotBlank(),
            onClear = { queryTextState.edit { delete(0, length ) } }
        )
    }
}

@Preview
@Composable
private fun SearchRecipeBarPreview() {
    SearchRecipeBar(queryTextState = TextFieldState(""))

}