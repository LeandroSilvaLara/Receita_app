package com.courselara.receitafacil.ui.presentation.features.recipes.detail.data.repository

import com.courselara.receitafacil.core.domain.model.RecipeDetailModel
import com.courselara.receitafacil.core.domain.model.SimplesResponseModel
import com.courselara.receitafacil.core.util.DispatcherProvider
import com.courselara.receitafacil.core.util.ServiceResult
import com.courselara.receitafacil.ui.presentation.features.recipes.detail.domain.repository.RecipeDetailRepository
import com.courselara.receitafacil.ui.presentation.features.recipes.detail.domain.source.RecipeDetailRemoteDataSource
import jakarta.inject.Inject
import kotlinx.coroutines.withContext

class RecipeDetailRepositoryImpl @Inject constructor(
    private val remoteDataSource: RecipeDetailRemoteDataSource,
    private val dispatcherProvider: DispatcherProvider
) : RecipeDetailRepository {

    override suspend fun getRecipeById(recipeId: String): ServiceResult<RecipeDetailModel> {
        return withContext(dispatcherProvider.io()) {
            remoteDataSource.getRecipeById(recipeId)
        }
    }

    override suspend fun deleteRecipe(recipeId: String): ServiceResult<SimplesResponseModel> {
        return withContext(dispatcherProvider.io()) {
            remoteDataSource.deleteRecipe(recipeId)
        }
    }
}

