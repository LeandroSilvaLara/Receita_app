package com.courselara.receitafacil.ui.presentation.features.recipes.list.data.repository

import com.courselara.receitafacil.core.domain.model.RecipesResponseModel
import com.courselara.receitafacil.core.util.DispatcherProvider
import com.courselara.receitafacil.core.util.ServiceResult
import com.courselara.receitafacil.ui.presentation.features.recipes.list.domain.repository.GetRecipesByUserRepository
import com.courselara.receitafacil.ui.presentation.features.recipes.list.domain.source.GetRecipesByUserRemoteDataSource
import jakarta.inject.Inject
import kotlinx.coroutines.withContext

class GetRecipesByUserRepositoryImpl @Inject constructor(
    private val remoteDataSource: GetRecipesByUserRemoteDataSource,
    private val dispatcherProvider: DispatcherProvider
) : GetRecipesByUserRepository {

    override suspend fun getRecipesByUser(category: Int?): ServiceResult<List<RecipesResponseModel>> {
        return withContext(dispatcherProvider.io()) {
            remoteDataSource.getRecipesByUser(category)
        }
    }
}