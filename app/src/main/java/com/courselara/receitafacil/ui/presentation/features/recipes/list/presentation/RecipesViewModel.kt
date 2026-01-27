package com.courselara.receitafacil.ui.presentation.features.recipes.list.presentation

import androidx.lifecycle.ViewModel
import com.courselara.receitafacil.ui.presentation.features.recipes.list.presentation.state.RecipesUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class RecipesViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(RecipesUiState())
    var uiState = _uiState.asStateFlow()

    private val _selectedCategory = MutableStateFlow<Int?>(null)
    var selectedCategory = _selectedCategory.asStateFlow()

}