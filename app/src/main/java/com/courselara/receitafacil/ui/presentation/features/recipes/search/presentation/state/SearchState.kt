package com.courselara.receitafacil.ui.presentation.features.recipes.search.presentation.state

sealed class SearchState {
    data object Empty : SearchState()
    data class Query(val query: String) : SearchState()


}
