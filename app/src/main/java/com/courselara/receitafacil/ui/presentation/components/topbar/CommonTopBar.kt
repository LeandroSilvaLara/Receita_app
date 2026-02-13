package com.courselara.receitafacil.ui.presentation.components.topbar

import android.R
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.courselara.receitafacil.ui.theme.ReceitaFacilAppTheme
import com.courselara.receitafacil.ui.theme.poppinsFOntFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonTopBar(
    modifier: Modifier = Modifier,
    title: String,
    enable: Boolean = true,
    actionImageVector: ImageVector? = null,
    navigationImageVector: ImageVector? = null,
    contentDescription: String? = null,
    onActionIconButton: () -> Unit = {},
    onNavigationIconButton: () -> Unit = {},
    action: @Composable RowScope.() -> Unit = {
        if (actionImageVector != null) {
            IconButton(
                onClick = { onActionIconButton() },
                enabled = enable
            ) {
                Icon(
                    imageVector = actionImageVector,
                    contentDescription = contentDescription,
                    tint = if (enable) MaterialTheme.colorScheme.onSurfaceVariant else Color.Transparent
                )
            }
        }
    },
) {
    TopAppBar(
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        title = {
            Text(
                text = title,
                maxLines = 1,
                fontFamily = poppinsFOntFamily,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        actions = action,
        navigationIcon = {
            if (navigationImageVector != null) {
                IconButton(
                    onClick = { onNavigationIconButton() },
                    enabled = enable
                ) {
                    Icon(
                        imageVector = navigationImageVector,
                        contentDescription = contentDescription,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    )
}

@Preview
@Composable
private fun CommonTopBarPreview() {
    ReceitaFacilAppTheme {
        CommonTopBar(
            title = "Ola Joao",
            enable = true,
            actionImageVector = Icons.Outlined.Save,
            navigationImageVector = Icons.AutoMirrored.Outlined.ArrowBack
        )
    }
}