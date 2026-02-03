package com.courselara.receitafacil.ui.presentation.features.recipes.add_update.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.courselara.receitafacil.core.domain.model.IngredientsModel
import com.courselara.receitafacil.core.sideeffects.SideEffect
import com.courselara.receitafacil.ui.presentation.features.recipes.add_update.domain.model.AddUpdateRecipeInputValidationType
import com.courselara.receitafacil.ui.presentation.features.recipes.add_update.domain.usecase.AddRecipeUseCase
import com.courselara.receitafacil.ui.presentation.features.recipes.add_update.domain.usecase.UpdateRecipeUseCase
import com.courselara.receitafacil.ui.presentation.features.recipes.add_update.domain.usecase.ValidateAddUpdateRecipeInputUseCase
import com.courselara.receitafacil.ui.presentation.features.recipes.add_update.domain.usecase.ValidateDialogInputUseCase
import com.courselara.receitafacil.ui.presentation.features.recipes.add_update.presentation.state.AddUpdateRecipeUiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import java.util.UUID
import javax.inject.Inject

class AddUpdateRecipeViewModel @Inject constructor(
    private val addRecipeUseCase: AddRecipeUseCase,
    private val updateRecipeUseCase: UpdateRecipeUseCase,
    private val validateDialogInputUseCase: ValidateDialogInputUseCase,
    private val validateAddUpdateRecipeInputUseCase: ValidateAddUpdateRecipeInputUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddUpdateRecipeUiState())
    val uiState = _uiState.asStateFlow()

    private val _sideEffectChannel = Channel<SideEffect>(capacity = Channel.BUFFERED)
    val sideEffectChannel = _sideEffectChannel.receiveAsFlow()

    var isAddIngredientDialogShown by mutableStateOf(false)
        private set

    var ingredients = mutableStateListOf<IngredientsModel>()

    fun onEvent(event: AddUpdateRecipeEvent) {
        when (event) {
            is AddUpdateRecipeEvent.OnNameInputChange -> {
                _uiState.update { it.copy(nameInput = event.nameValue) }
                checkInputValidation()
            }

            is AddUpdateRecipeEvent.OnCategoryInputChange -> {
                _uiState.update { it.copy(categoryInput = event.nameValue) }
                checkInputValidation()
            }

            is AddUpdateRecipeEvent.OnPreparationTimeInputChange -> {
                _uiState.update { it.copy(preparationTimeInput = event.nameValue) }
                checkInputValidation()

            }

            is AddUpdateRecipeEvent.OnPreparationModeInputChange -> {
                _uiState.update { it.copy(preparationModeInput = event.nameValue) }
                checkInputDialogValidation()
            }

            is AddUpdateRecipeEvent.OnIngredientProductNameInputChange -> {
                _uiState.update { it.copy(ingredientsProductNameInput = event.nameValue) }
                checkInputDialogValidation()
            }

            is AddUpdateRecipeEvent.OnIngredientProductQuantityInputChange -> {
                _uiState.update { it.copy(ingredientsProductQuantityInput = event.nameValue) }
            }

            is AddUpdateRecipeEvent.onRemoveIngredient -> {
                ingredients.remove(event.ingredientsModel)
            }

            is AddUpdateRecipeEvent.OnAddOrUpdateRecipe -> {
                addOrUpdateRecipe()
            }

            is AddUpdateRecipeEvent.OnAddIngredient -> {
                addIngredient()
            }
        }

    }

    fun onDismissDialog() {
        isAddIngredientDialogShown = false
    }

    fun onOpenDialog() {
        isAddIngredientDialogShown = true
    }

    private fun addIngredient() {
        if (_uiState.value.isInputDialogValid) {
            val id = UUID.randomUUID().toString()
            val productName = _uiState.value.ingredientsProductNameInput
            val productQuantity = _uiState.value.ingredientsProductQuantityInput
            ingredients.add(
                IngredientsModel(
                    id = id,
                    name = productName,
                    quantity = productQuantity
                )
            )
            onDismissDialog()
            clearFields()

        }
    }

    private fun clearFields() {

    }


    private fun addOrUpdateRecipe() {
        if (_uiState.value.currentRecipeId.isEmpty()) {
            addRecipe()
        } else {
            updateRecipe()
        }
    }

    private fun updateRecipe() {

    }

    private fun addRecipe() {

    }

    private fun checkInputValidation() {
        val resultValidation = validateAddUpdateRecipeInputUseCase.invoke(
            parameters = ValidateAddUpdateRecipeInputUseCase.Parameters(
                name = _uiState.value.nameInput,
                category = _uiState.value.categoryInput,
                preparationModel = _uiState.value.preparationModeInput,
                preparationTime = _uiState.value.preparationTimeInput,

                )
        )
        processInputValidation(resultValidation)
    }

    private fun processInputValidation(type: AddUpdateRecipeInputValidationType) {
        _uiState.update {
            when (type) {
                AddUpdateRecipeInputValidationType.EmptyField -> {
                    it.copy(errorMessageInput = "Preencha todos os campos", isInputValid = false)
                }

                AddUpdateRecipeInputValidationType.Valid -> {
                    it.copy(errorMessageInput = null, isInputValid = true)
                }

                else -> {
                    it.copy(errorMessageInput = null, isInputValid = true)
                }

            }
        }
    }

    private fun checkInputDialogValidation() {
        val resultValidation = validateDialogInputUseCase.invoke(
            parameters = ValidateDialogInputUseCase.Parameters(
                ingredientsProductName = _uiState.value.ingredientsProductNameInput,
                ingredientsProductQuantity = _uiState.value.ingredientsProductQuantityInput
            )
        )
        processDialogInputValidation(resultValidation)
    }

    private fun processDialogInputValidation(type: AddUpdateRecipeInputValidationType) {
        _uiState.update {
            when (type) {
                AddUpdateRecipeInputValidationType.EmptyDialogField -> {
                    it.copy(
                        errorMessageDialogInput = "Preencha todos os campos",
                        isInputDialogValid = false
                    )
                }

                AddUpdateRecipeInputValidationType.Valid -> {
                    it.copy(errorMessageDialogInput = null, isInputDialogValid = true)
                }

                else -> {
                    it.copy(errorMessageDialogInput = null, isInputDialogValid = true)
                }

            }
        }
    }
}