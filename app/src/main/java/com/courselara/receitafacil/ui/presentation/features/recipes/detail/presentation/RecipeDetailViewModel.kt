package com.courselara.receitafacil.ui.presentation.features.recipes.detail.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.courselara.receitafacil.core.sideeffects.SideEffect
import com.courselara.receitafacil.core.util.extensions.observeState
import com.courselara.receitafacil.ui.presentation.features.recipes.detail.domain.use_cases.DeleteRecipeUseCase
import com.courselara.receitafacil.ui.presentation.features.recipes.detail.domain.use_cases.GetRecipeByIdUseCase
import com.courselara.receitafacil.ui.presentation.features.recipes.detail.presentation.state.RecipeDetailState
import com.courselara.receitafacil.ui.presentation.navigation.screens.HomeScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val deleteRecipeUseCase: DeleteRecipeUseCase,
    private val getRecipeByIdUseCase: GetRecipeByIdUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RecipeDetailState())
    var uiState = _uiState.asStateFlow()

    private val _sideEffectChannel = Channel<SideEffect>(capacity = Channel.BUFFERED)
    val sideEffectChannel = _sideEffectChannel.receiveAsFlow()

    private val args = savedStateHandle.toRoute<HomeScreens.RecipeDetailsScreen>()

    init {
        fetchData()
    }


    fun onEvent(event: RecipeDetailEvent) {
        when (event) {
            is RecipeDetailEvent.OnShowDialog -> {
                _uiState.update { it.copy(dialogState = true) }
            }

            is RecipeDetailEvent.OnDismissDialog -> {
                _uiState.update { it.copy(dialogState = false) }
            }

            is RecipeDetailEvent.OnDeleteRecipe -> {
                deleteRecipe(event.recipeId)
                _uiState.update { it.copy(dialogState = false) }
            }

        }
    }

    private fun fetchData() {
        viewModelScope.launch {
            getRecipeByIdUseCase(GetRecipeByIdUseCase.Parameters(args.recipeId))
                .observeState(
                    onLoading = {
                        _uiState.update { it.copy(isLoading = true) }

                    },
                    onFailure = { error ->
                        _uiState.update { it.copy(isLoading = false, errorMessage = error.message) }
                    },
                    onSuccess = { recipeDetailModel ->
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                recipeDetail = recipeDetailModel
                            )
                        }

                    }

                )
        }
    }

    private fun deleteRecipe(recipeId: String) {
        viewModelScope.launch {
            deleteRecipeUseCase.invoke(DeleteRecipeUseCase.Parameters(recipeId))
                .observeState(
                    onLoading = {
                        _uiState.update { it.copy(isLoading = true) }

                    },
                    onFailure = { error ->
                        _uiState.update { it.copy(isLoading = false, errorMessage = error.message) }
                    },
                    onSuccess = { simplesResponseModel ->
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                successfullyDeletedRecipe = simplesResponseModel.isSuccessFul
                            )
                        }
                        _sideEffectChannel.send(SideEffect.ShowToast(simplesResponseModel.message))
                    }

                )
        }
    }

}