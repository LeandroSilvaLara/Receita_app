package com.courselara.receitafacil.ui.presentation.features.recipes.add.data.repository

import com.courselara.receitafacil.core.domain.model.SimplesResponseModel
import com.courselara.receitafacil.core.util.DispatcherProvider
import com.courselara.receitafacil.core.util.ServiceResult
import com.courselara.receitafacil.ui.presentation.features.recipes.add.domain.model.AddUpdateRecipeRequestModel
import com.courselara.receitafacil.ui.presentation.features.recipes.add.domain.repository.AddUpdateRecipeRepository
import com.courselara.receitafacil.ui.presentation.features.recipes.add.domain.source.AddUpdateRecipeRemoteDataSource
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddUpdateRecipeRepositoryImpl @Inject constructor(
    private val remoteDataSource: AddUpdateRecipeRemoteDataSource,
    private val dispatcherProvider: DispatcherProvider
) : AddUpdateRecipeRepository {
    override suspend fun addRecipe(addUpdateRecipeRequestModel: AddUpdateRecipeRequestModel): ServiceResult<SimplesResponseModel>? {
        return withContext(dispatcherProvider.io()) {
            remoteDataSource.addRecipe(addUpdateRecipeRequestModel)
        }
    }

    override suspend fun updateRecipe(
        recipeId: String,
        addUpdateRecipeRequestModel: AddUpdateRecipeRequestModel
    ): ServiceResult<SimplesResponseModel> {
        return withContext(dispatcherProvider.io()) {
            remoteDataSource.updateRecipe(recipeId, addUpdateRecipeRequestModel)
        }
    }

}