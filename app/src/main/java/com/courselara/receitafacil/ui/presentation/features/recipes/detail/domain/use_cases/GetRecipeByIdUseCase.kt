package com.courselara.receitafacil.ui.presentation.features.recipes.detail.domain.use_cases

import com.courselara.receitafacil.core.domain.model.RecipeDetailModel
import com.courselara.receitafacil.core.domain.model.SimplesResponseModel
import com.courselara.receitafacil.core.util.ResponseData
import com.courselara.receitafacil.core.util.ServiceResult
import com.courselara.receitafacil.core.util.Task
import com.courselara.receitafacil.ui.presentation.features.recipes.detail.di.RecipeDetailModule
import com.courselara.receitafacil.ui.presentation.features.recipes.detail.domain.repository.RecipeDetailRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

interface GetRecipeByIdUseCase {
    operator fun invoke(parameters: Parameters): Flow<ResponseData<RecipeDetailModel>>
    data class Parameters(val recipeId: String)
}

class GetRecipeByIdUseCaseImpl @Inject constructor(
    private val recipeDetailRepository: RecipeDetailRepository
) : GetRecipeByIdUseCase, Task<GetRecipeByIdUseCase.Parameters, RecipeDetailModel>() {

    override suspend fun executeTask(parameters: GetRecipeByIdUseCase.Parameters): ResponseData<RecipeDetailModel> {
        return try {
            when (val serviceResult =
                recipeDetailRepository.getRecipeById(recipeId = parameters.recipeId)) {
                is ServiceResult.Success -> {
                    ResponseData.Success(serviceResult.data)
                }

                is ServiceResult.Error -> {
                    ResponseData.Error(Exception(serviceResult.message))
                }
            }
        } catch (e: Exception) {
            ResponseData.Error(e)
        }
    }
}