package com.courselara.receitafacil.ui.presentation.features.recipes.detail.domain.use_cases

import com.courselara.receitafacil.core.domain.model.SimplesResponseModel
import com.courselara.receitafacil.core.util.ResponseData
import com.courselara.receitafacil.core.util.ServiceResult
import com.courselara.receitafacil.core.util.Task
import com.courselara.receitafacil.ui.presentation.features.recipes.detail.domain.repository.RecipeDetailRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

/**
 * Use case responsible for deleting a specific recipe by its identifier.
 *
 * This use case encapsulates the business logic for removing a recipe from the system,
 * returning a flow that emits the state of the operation.
 */
interface DeleteRecipeUseCase {
    operator fun invoke(parameters: Parameters): Flow<ResponseData<SimplesResponseModel>>
    data class Parameters(val recipeId: String)
}

class DeleteRecipeUseCaseImpl @Inject constructor(
    private val recipeDetailRepository: RecipeDetailRepository
) : DeleteRecipeUseCase, Task<DeleteRecipeUseCase.Parameters, SimplesResponseModel>() {

    override suspend fun executeTask(parameters: DeleteRecipeUseCase.Parameters): ResponseData<SimplesResponseModel> {
        return try {
            when (val serviceResult =
                recipeDetailRepository.deleteRecipe(recipeId = parameters.recipeId)) {
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