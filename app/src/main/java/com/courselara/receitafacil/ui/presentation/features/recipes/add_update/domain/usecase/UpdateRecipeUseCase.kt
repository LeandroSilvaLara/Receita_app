package com.courselara.receitafacil.ui.presentation.features.recipes.add_update.domain.usecase

import com.courselara.receitafacil.core.domain.model.SimplesResponseModel
import com.courselara.receitafacil.core.util.ResponseData
import com.courselara.receitafacil.core.util.ResponseData.Empty
import com.courselara.receitafacil.core.util.ResponseData.Error
import com.courselara.receitafacil.core.util.ResponseData.Success
import com.courselara.receitafacil.core.util.ServiceResult
import com.courselara.receitafacil.core.util.Task
import com.courselara.receitafacil.ui.presentation.features.recipes.add_update.domain.model.AddUpdateRecipeRequestModel
import com.courselara.receitafacil.ui.presentation.features.recipes.add_update.domain.repository.AddUpdateRecipeRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

interface UpdateRecipeUseCase{
    operator fun invoke(parameters: Parameters): Flow<ResponseData<SimplesResponseModel>>
    data class Parameters (
        val recipeId: String,
        val addUpdateRecipeRequestModel: AddUpdateRecipeRequestModel
    )
}

class UpdateRecipeUseCaseImpl @Inject constructor(
    private val addUpdateRecipeRepository: AddUpdateRecipeRepository
) : UpdateRecipeUseCase, Task<UpdateRecipeUseCase.Parameters, SimplesResponseModel>() {
    override suspend fun executeTask(parameters: UpdateRecipeUseCase.Parameters): ResponseData<SimplesResponseModel> {
        return try {
            when (val response =
                addUpdateRecipeRepository.updateRecipe(parameters.recipeId, parameters.addUpdateRecipeRequestModel)) {
                is ServiceResult.Success -> {
                    Success(response.data)
                }

                is ServiceResult.Error -> {
                    Error(Throwable(response.message))
                }

                else -> {
                    Empty
                }

            }
        } catch (e: Exception) {
            return ResponseData.Error(e)
        }
    }

}