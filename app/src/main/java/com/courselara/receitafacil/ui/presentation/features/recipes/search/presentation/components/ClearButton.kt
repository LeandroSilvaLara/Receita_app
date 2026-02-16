package com.courselara.receitafacil.ui.presentation.features.recipes.search.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DeleteOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ClearButton(
    modifier: Modifier = Modifier,
    isVisible: Boolean,
    onClear: () -> Unit
) {
    AnimatedVisibility(visible = isVisible) {
        Icon(
            imageVector = Icons.Outlined.DeleteOutline,
            contentDescription = "Icon Delete",
            modifier = modifier.clickable(onClick = onClear),
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}