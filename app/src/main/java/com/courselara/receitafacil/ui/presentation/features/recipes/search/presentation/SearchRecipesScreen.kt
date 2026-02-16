package com.courselara.receitafacil.ui.presentation.features.recipes.search.presentation

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.courselara.receitafacil.ui.MainUiState
import com.courselara.receitafacil.ui.presentation.components.topbar.CommonTopBar
import com.courselara.receitafacil.ui.presentation.features.recipes.search.presentation.components.SearchRecipeBar
import com.courselara.receitafacil.ui.presentation.features.recipes.search.presentation.components.SearchRecipesContent
import com.courselara.receitafacil.ui.presentation.features.recipes.search.presentation.state.SearchRecipesState
import com.courselara.receitafacil.ui.theme.ReceitaFacilAppTheme

@Composable
fun SearchRecipesScreen(
    modifier: Modifier = Modifier,
    uiState: SearchRecipesState,
    onNavigateUp: () -> Unit,
    onNavigateToRecipeDetailScreen: (String) -> Unit,
    onEvent: (SearchRecipesEvent) -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        onEvent(SearchRecipesEvent.OnObserverSearch)
    }


    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            CommonTopBar(
                title = "Pesquisar Receitas",
                navigationImageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                onNavigationIconButton = onNavigateUp
            )
        },
        content = { paddingValues ->
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                SearchRecipeBar(
                    queryTextState = uiState.queryTextState
                )

                SearchRecipesContent(
                    uiState = uiState,
                    onNavigateToRecipeDetail = onNavigateToRecipeDetailScreen
                )

            }

        }
    )

}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SearchRecipesScreenPreview() {
    ReceitaFacilAppTheme {
        SearchRecipesScreen(
            uiState = SearchRecipesState(
                errorMessage = "Um error ocorreu"
            ),
            onNavigateUp = {},
            onNavigateToRecipeDetailScreen = {},
            onEvent = {}
        )
    }
}