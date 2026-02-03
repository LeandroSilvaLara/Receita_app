package com.courselara.receitafacil.ui.presentation.features.recipes.add_update.domain.usecase

import com.courselara.receitafacil.ui.presentation.features.recipes.add_update.domain.model.AddUpdateRecipeInputValidationType

interface ValidateDialogInputUseCase {
    operator fun invoke(parameters: Parameters): AddUpdateRecipeInputValidationType
    data class Parameters(
        val ingredientsProductName: String,
        val ingredientsProductQuantity: String,
    )

}

class ValidateDialogInputUseCaseImpl : ValidateDialogInputUseCase {
    override fun invoke(parameters: ValidateDialogInputUseCase.Parameters): AddUpdateRecipeInputValidationType {
        return if (parameters.ingredientsProductName.isEmpty() || parameters.ingredientsProductQuantity.isEmpty()
                ) {
            AddUpdateRecipeInputValidationType.EmptyDialogField
        } else {
            AddUpdateRecipeInputValidationType.Valid
        }
    }
}
