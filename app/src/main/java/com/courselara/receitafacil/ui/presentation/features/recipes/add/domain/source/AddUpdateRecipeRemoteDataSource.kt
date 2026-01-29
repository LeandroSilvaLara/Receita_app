package com.courselara.receitafacil.ui.presentation.features.recipes.add.domain.source

import com.courselara.receitafacil.core.domain.model.SimplesResponseModel
import com.courselara.receitafacil.core.util.ServiceResult
import com.courselara.receitafacil.ui.presentation.features.recipes.add.domain.model.AddUpdateRecipeRequestModel

interface AddUpdateRecipeRemoteDataSource {
    suspend fun addRecipe(addUpdateRecipeRequestModel: AddUpdateRecipeRequestModel): ServiceResult<SimplesResponseModel>?
    suspend fun updateRecipe(recipeId: String, addUpdateRecipeRequestModel: AddUpdateRecipeRequestModel): ServiceResult<SimplesResponseModel>
}