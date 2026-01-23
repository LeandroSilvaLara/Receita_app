package com.courselara.receitafacil.ui.presentation.features.recipes.list.domain.usecase

import com.courselara.receitafacil.core.domain.model.RecipesResponseModel
import com.courselara.receitafacil.core.util.ResponseData
import com.courselara.receitafacil.core.util.ServiceResult
import com.courselara.receitafacil.core.util.Task
import com.courselara.receitafacil.ui.presentation.features.recipes.list.domain.repository.GetRecipesByUserRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

interface GetRecipesByUserUseCase {
    operator fun invoke(parameters: Parameters): Flow<ResponseData<List<RecipesResponseModel>>>
    data class Parameters(val category: Int?)
}

class GetRecipesByUSerUseCaseImpl @Inject constructor(
    private val getRecipesByUserRepository: GetRecipesByUserRepository
) : GetRecipesByUserUseCase, Task<GetRecipesByUserUseCase.Parameters, List<RecipesResponseModel>>() {
    override suspend fun executeTask(parameters: GetRecipesByUserUseCase.Parameters): ResponseData<List<RecipesResponseModel>> {
        return try {
            when (val response = getRecipesByUserRepository.getRecipesByUser(parameters.category)) {
                is ServiceResult.Success -> {
                    if (response.data.isNotEmpty()) {
                        ResponseData.Empty
                    } else {
                        ResponseData.Success(response.data)
                    }
                }

                is ServiceResult.Error -> {
                    ResponseData.Error(Throwable(response.message))
                }
            }
        } catch (e: Throwable) {
            return ResponseData.Error(e)
        }
    }

}