package com.courselara.receitafacil.ui.presentation.features.recipes.add_update.domain.model

/**
 * Represents the validation states for the input fields in the Add/Update recipe screen.
 *
 * @property EmptyField Indicates that a required primary field is empty.
 * @property EmptyDialogField Indicates that a required field within a modal or dialog is empty.
 * @property Valid Indicates that all input fields have passed validation requirements.
 */
enum class AddUpdateRecipeInputValidationType {
    EmptyField,
    EmptyDialogField,
    Valid
}