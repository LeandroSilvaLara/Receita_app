package com.courselara.receitafacil.ui.presentation.features.recipes.add_update.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.courselara.receitafacil.core.domain.model.CategoryEnum
import com.courselara.receitafacil.core.domain.model.IngredientsModel
import com.courselara.receitafacil.core.sideeffects.SideEffect
import com.courselara.receitafacil.core.util.extensions.observeState
import com.courselara.receitafacil.ui.presentation.features.recipes.add_update.domain.model.AddUpdateRecipeInputValidationType
import com.courselara.receitafacil.ui.presentation.features.recipes.add_update.domain.model.AddUpdateRecipeRequestModel
import com.courselara.receitafacil.ui.presentation.features.recipes.add_update.domain.usecase.AddRecipeUseCase
import com.courselara.receitafacil.ui.presentation.features.recipes.add_update.domain.usecase.UpdateRecipeUseCase
import com.courselara.receitafacil.ui.presentation.features.recipes.add_update.domain.usecase.ValidateAddUpdateRecipeInputUseCase
import com.courselara.receitafacil.ui.presentation.features.recipes.add_update.domain.usecase.ValidateDialogInputUseCase
import com.courselara.receitafacil.ui.presentation.features.recipes.add_update.presentation.state.AddUpdateRecipeUiState
import com.courselara.receitafacil.ui.presentation.features.recipes.detail.domain.use_cases.GetRecipeByIdUseCase
import com.courselara.receitafacil.ui.presentation.navigation.screens.HomeScreens
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

class AddUpdateRecipeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val addRecipeUseCase: AddRecipeUseCase,
    private val updateRecipeUseCase: UpdateRecipeUseCase,
    private val getRecipeByIdUseCase: GetRecipeByIdUseCase,
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

    val recipeId = savedStateHandle.toRoute<HomeScreens.AddRecipeScreen>().recipeId ?: ""

    init {
        loadRecipeForEditing(recipeId)
    }

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

    private fun loadRecipeForEditing(recipeId: String) {
        if (recipeId.isNotEmpty()) {
            viewModelScope.launch {
                getRecipeByIdUseCase.invoke(
                    GetRecipeByIdUseCase.Parameters(recipeId)
                ).observeState(
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
                                currentRecipeId = recipeDetailModel.id
                            )
                        }

                        onEvent(AddUpdateRecipeEvent.OnNameInputChange(recipeDetailModel.name))
                        onEvent(AddUpdateRecipeEvent.OnCategoryInputChange(recipeDetailModel.category))
                        onEvent(AddUpdateRecipeEvent.OnPreparationModeInputChange(recipeDetailModel.preparationModel))
                        onEvent(AddUpdateRecipeEvent.OnPreparationTimeInputChange(recipeDetailModel.preparationTime))

                        recipeDetailModel.ingredients.forEach { ingredientModel ->
                            ingredients.add(
                                IngredientsModel(
                                    id = ingredientModel.id,
                                    name = ingredientModel.name,
                                    quantity = ingredientModel.quantity
                                )
                            )
                        }
                        processInputValidation(AddUpdateRecipeInputValidationType.Valid)
                        processDialogInputValidation(AddUpdateRecipeInputValidationType.Valid)
                    }
                )
            }
        }
    }


    private fun addIngredient() {
        if (_uiState.value.isInputDialogValid) {
            val id = UUID.randomUUID().toString()
            val productName = _uiState.value.ingredientsProductNameInput
            val productQuantity = _uiState.value.ingredientsProductQuantityInput
            val ingredientsModel = IngredientsModel(
                id = id,
                name = productName,
                quantity = productQuantity
            )
            ingredients.add(
                ingredientsModel
            )
            onDismissDialog()
            clearFields()
        } else {
            return
        }
    }

    private fun clearFields() {
        _uiState.update { it.copy(ingredientsProductNameInput = "", ingredientsProductQuantityInput = "") }
    }


    private fun addOrUpdateRecipe() {
        if (_uiState.value.currentRecipeId.isEmpty()) {
            addRecipe()
        } else {
            updateRecipe()
        }
    }

    private fun addRecipe() {
        viewModelScope.launch {
            addRecipeUseCase.invoke(
                AddRecipeUseCase.Parameters(
                    AddUpdateRecipeRequestModel(
                        name = _uiState.value.nameInput,
                        category = CategoryEnum.fromDescription(_uiState.value.categoryInput)?.value
                            ?: 0,
                        preparationModel = _uiState.value.preparationModeInput,
                        preparationTime = _uiState.value.preparationTimeInput
                    )
                )
            ).observeState(
                onLoading = {
                    _uiState.update { it.copy(isLoading = true) }
                },
                onFailure = { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false, errorMessageRegisterProcess =
                                error.message.toString()
                        )
                    }
                },
                onSuccess = { response ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isOperationSuccessful = response.isSuccessFul,
                        )
                    }
                    _sideEffectChannel.send(SideEffect.ShowToast(response.message))
                }
            )
        }
    }

    private fun updateRecipe() {
        viewModelScope.launch {
            updateRecipeUseCase.invoke(
                UpdateRecipeUseCase.Parameters(
                    recipeId = _uiState.value.currentRecipeId,
                    addUpdateRecipeRequestModel =
                        AddUpdateRecipeRequestModel(
                            name = _uiState.value.nameInput,
                            category = CategoryEnum.fromDescription(_uiState.value.categoryInput)?.value
                                ?: 0,
                            preparationModel = _uiState.value.preparationModeInput,
                            preparationTime = _uiState.value.preparationTimeInput,
                            ingredients = ingredients
                        )
                )
            ).observeState(
                onLoading = {
                    _uiState.update { it.copy(isLoading = true) }
                },
                onFailure = { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false, errorMessageRegisterProcess =
                                error.message.toString()
                        )
                    }
                },
                onSuccess = { response ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isOperationSuccessful = response.isSuccessFul,
                        )
                    }
                    _sideEffectChannel.send(SideEffect.ShowToast(response.message))
                }
            )
        }
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