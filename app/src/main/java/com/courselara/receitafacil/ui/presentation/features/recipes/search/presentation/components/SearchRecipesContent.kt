package com.courselara.receitafacil.ui.presentation.features.recipes.search.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.courselara.receitafacil.ui.presentation.components.state.LoadingIndicator
import com.courselara.receitafacil.ui.presentation.features.recipes.search.presentation.state.SearchRecipesState
import com.courselara.receitafacil.ui.theme.poppinsFOntFamily

@Composable
fun SearchRecipesContent(
    modifier: Modifier = Modifier,
    uiState: SearchRecipesState,
    onNavigateToRecipeDetail: (String) -> Unit
) {
    when {
        uiState.isLoading -> {
            LoadingIndicator()
        }

        uiState.isEmpty -> {
            SearchRecipesEmptyState(modifier = modifier.padding(12.dp))
        }

        uiState.results.isNotEmpty() -> {
            SearchRecipesContent(
                results = uiState.results,
                onNavigateToRecipeDetail = onNavigateToRecipeDetail
            )
        }

        else -> {
            Text(
                text = "Pesquise  por nome ou ingrediente",
                maxLines = 2,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontFamily = poppinsFOntFamily,
                modifier = modifier.fillMaxWidth().padding(8.dp)
            )
        }
    }
}