package com.courselara.receitafacil.ui.presentation.features.recipes.add.data.source

import com.courselara.receitafacil.core.data.remote.RecipesServiceApi
import com.courselara.receitafacil.core.domain.exceptions.ErrorResponseException
import com.courselara.receitafacil.core.domain.model.SimplesResponseModel
import com.courselara.receitafacil.core.util.ServiceResult
import com.courselara.receitafacil.ui.presentation.features.auth.register.data.mapper.toSimplesResponseModel
import com.courselara.receitafacil.ui.presentation.features.recipes.add.data.mappers.toAddUpdateRecipeRequest
import com.courselara.receitafacil.ui.presentation.features.recipes.add.domain.model.AddUpdateRecipeRequestModel
import com.courselara.receitafacil.ui.presentation.features.recipes.add.domain.source.AddUpdateRecipeRemoteDataSource
import io.ktor.client.plugins.ResponseException
import javax.inject.Inject

class AddUpdateRecipeRemoteDataSourceImpl @Inject constructor(
    private val serviceApi: RecipesServiceApi
) : AddUpdateRecipeRemoteDataSource {
    override suspend fun addRecipe(addUpdateRecipeRequestModel: AddUpdateRecipeRequestModel): ServiceResult<SimplesResponseModel>? {

        return try {
            val request = addUpdateRecipeRequestModel.toAddUpdateRecipeRequest()
            val response = serviceApi.addRecipe(request)
            if (response.isSuccessFul) {
                ServiceResult.Success(response.toSimplesResponseModel())
            } else {
                ServiceResult.Error(message = response.message)
            }

        } catch (e: ResponseException) {
            ServiceResult.Error(e.response.status.value.toString(), e.response.status.description)
        } catch (e: ErrorResponseException) {
            ServiceResult.Error(code = e.error.httpCode.toString(), message = e.error.message)
        }
    }

    override suspend fun updateRecipe(
        recipeId: String,
        addUpdateRecipeRequestModel: AddUpdateRecipeRequestModel
    ): ServiceResult<SimplesResponseModel> {
        return try {

            val request = addUpdateRecipeRequestModel.toAddUpdateRecipeRequest()
            val response = serviceApi.updateRecipe(recipeId, request)
            if (response.isSuccessFul) {
                ServiceResult.Success(response.toSimplesResponseModel())
            } else {
                ServiceResult.Error(message = response.message)
            }
        } catch (e: ResponseException) {
            ServiceResult.Error(e.response.status.value.toString(), e.response.status.description)
        } catch (e: ErrorResponseException) {
            ServiceResult.Error(code = e.error.httpCode.toString(), message = e.error.message)
        }
    }

}