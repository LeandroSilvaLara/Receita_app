package com.courselara.receitafacil.ui.presentation.features.recipes.list.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.courselara.receitafacil.R
import com.courselara.receitafacil.ui.presentation.components.state.EmptyState

@Composable
fun RecipesEmptyState(
    modifier: Modifier = Modifier
) {
    EmptyState(
        image = painterResource(id = R.drawable.ic_empty_state_recipes),
        title = stringResource(id = R.string.empty_recipe_text),
        modifier = modifier
    )
}