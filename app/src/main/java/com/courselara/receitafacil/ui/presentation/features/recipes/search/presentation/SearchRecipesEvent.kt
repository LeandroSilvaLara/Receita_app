package com.courselara.receitafacil.ui.presentation.features.recipes.search.presentation

sealed class SearchRecipesEvent {
    data object OnObserverSearch : SearchRecipesEvent()
}