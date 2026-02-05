package com.courselara.receitafacil.ui.presentation.features.recipes.detail.data.source

import com.courselara.receitafacil.core.data.remote.RecipesServiceApi
import com.courselara.receitafacil.core.domain.exceptions.ErrorResponseException
import com.courselara.receitafacil.core.domain.model.RecipeDetailModel
import com.courselara.receitafacil.core.domain.model.SimplesResponseModel
import com.courselara.receitafacil.core.util.ServiceResult
import com.courselara.receitafacil.ui.presentation.features.auth.register.data.mapper.toSimplesResponseModel
import com.courselara.receitafacil.ui.presentation.features.recipes.add_update.data.mappers.toAddUpdateRecipeRequest
import com.courselara.receitafacil.ui.presentation.features.recipes.add_update.data.mappers.toRecipeDetailModel
import com.courselara.receitafacil.ui.presentation.features.recipes.detail.domain.source.RecipeDetailRemoteDataSource
import io.ktor.client.plugins.ResponseException
import javax.inject.Inject

class RecipeDetailRemoteDataSourceImpl @Inject constructor(
    private val serviceApi: RecipesServiceApi
) : RecipeDetailRemoteDataSource {
    override suspend fun getRecipeById(recipeId: String): ServiceResult<RecipeDetailModel> {
        return try {
            val recipeDetailResponse = serviceApi.getRecipeById(recipeId)

            ServiceResult.Success(recipeDetailResponse.toRecipeDetailModel())

        } catch (e: ResponseException) {
            ServiceResult.Error(e.response.status.value.toString(), e.response.status.description)
        } catch (e: ErrorResponseException) {
            ServiceResult.Error(code = e.error.httpCode.toString(), message = e.error.message)
        }
    }

    override suspend fun deleteRecipe(recipeId: String): ServiceResult<SimplesResponseModel> {
        return try {

            val simplesResponse = serviceApi.deleteRecipe(recipeId)
            if (simplesResponse.isSuccessFul) {
                ServiceResult.Success(simplesResponse.toSimplesResponseModel())
            } else {
                ServiceResult.Error(message = simplesResponse.message)
            }
        } catch (e: ResponseException) {
            ServiceResult.Error(e.response.status.value.toString(), e.response.status.description)
        } catch (e: ErrorResponseException) {
            ServiceResult.Error(code = e.error.httpCode.toString(), message = e.error.message)
        }
    }
}