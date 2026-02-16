package com.courselara.receitafacil.ui.presentation.features.recipes.search.data.repository

import com.courselara.receitafacil.core.domain.model.RecipesResponseModel
import com.courselara.receitafacil.core.util.DispatcherProvider
import com.courselara.receitafacil.core.util.ServiceResult
import com.courselara.receitafacil.ui.presentation.features.recipes.search.domain.repository.SearchRecipesRepository
import com.courselara.receitafacil.ui.presentation.features.recipes.search.domain.source.SearchRecipesRemoteDataSource
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchRecipesRepositoryImpl @Inject constructor(
    private val remoteDataSource: SearchRecipesRemoteDataSource,
    private val dispatcherProvider: DispatcherProvider
): SearchRecipesRepository {
    override suspend fun search(nameOrIngredient: String): ServiceResult<List<RecipesResponseModel>> {
        return withContext(dispatcherProvider.io()) {
            remoteDataSource.search(nameOrIngredient)
        }
    }
}