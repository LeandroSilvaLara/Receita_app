package com.courselara.receitafacil.ui.presentation.features.recipes.search.presentation.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.courselara.receitafacil.R
import com.courselara.receitafacil.ui.presentation.components.state.EmptyState
import com.courselara.receitafacil.ui.theme.poppinsFOntFamily

@Composable
fun SearchRecipesEmptyState(
    modifier: Modifier = Modifier,
) {
    EmptyState(
        image = painterResource(R.drawable.ic_empty_state_recipes),
        title = stringResource(R.string.empty_recipe_text),
        subtitle = {
            Text(
                text = stringResource(R.string.try_again_text),
                fontFamily = poppinsFOntFamily,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        modifier = modifier
    )
}