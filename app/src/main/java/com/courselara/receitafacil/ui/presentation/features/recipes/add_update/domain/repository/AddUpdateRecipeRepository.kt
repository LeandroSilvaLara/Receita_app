package com.courselara.receitafacil.ui.presentation.features.recipes.add_update.domain.repository

import com.courselara.receitafacil.core.domain.model.SimplesResponseModel
import com.courselara.receitafacil.core.util.ServiceResult
import com.courselara.receitafacil.ui.presentation.features.recipes.add_update.domain.model.AddUpdateRecipeRequestModel

interface AddUpdateRecipeRepository {
    suspend fun addRecipe(addUpdateRecipeRequestModel: AddUpdateRecipeRequestModel): ServiceResult<SimplesResponseModel>?
    suspend fun updateRecipe(recipeId: String, addUpdateRecipeRequestModel: AddUpdateRecipeRequestModel): ServiceResult<SimplesResponseModel>
}