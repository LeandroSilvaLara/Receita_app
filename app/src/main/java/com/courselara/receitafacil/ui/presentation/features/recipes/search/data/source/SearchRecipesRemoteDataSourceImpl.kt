package com.courselara.receitafacil.ui.presentation.features.recipes.search.data.source

import com.courselara.receitafacil.core.data.remote.RecipesServiceApi
import com.courselara.receitafacil.core.domain.exceptions.ErrorResponseException
import com.courselara.receitafacil.core.domain.model.RecipesResponseModel
import com.courselara.receitafacil.core.util.ServiceResult
import com.courselara.receitafacil.ui.presentation.features.recipes.list.data.mappers.toRecipesResponseModel
import com.courselara.receitafacil.ui.presentation.features.recipes.search.domain.source.SearchRecipesRemoteDataSource
import io.ktor.client.plugins.ResponseException
import javax.inject.Inject

class SearchRecipesRemoteDataSourceImpl @Inject constructor(
    private val serviceApi: RecipesServiceApi
): SearchRecipesRemoteDataSource {

    override suspend fun search(nameOrIngredient: String): ServiceResult<List<RecipesResponseModel>> {
        return try {

            val recipesResponse = serviceApi.searchRecipes(nameOrIngredient)
            val recipesResponseModel = recipesResponse.map {
                it.toRecipesResponseModel()
            }
            ServiceResult.Success(recipesResponseModel)

        } catch (e: ResponseException) {
            ServiceResult.Error(e.response.status.value.toString(), e.response.status.description)
        } catch (e: ErrorResponseException) {
            ServiceResult.Error(code = e.error.httpCode.toString(), message = e.error.message)
        }
    }
}