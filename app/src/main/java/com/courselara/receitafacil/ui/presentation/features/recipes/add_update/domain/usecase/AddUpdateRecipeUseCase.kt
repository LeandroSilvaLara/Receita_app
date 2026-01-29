package com.courselara.receitafacil.ui.presentation.features.recipes.add_update.domain.usecase

import com.courselara.receitafacil.core.domain.model.SimplesResponseModel
import com.courselara.receitafacil.core.util.ResponseData
import com.courselara.receitafacil.core.util.ResponseData.*
import com.courselara.receitafacil.core.util.ServiceResult
import com.courselara.receitafacil.core.util.Task
import com.courselara.receitafacil.ui.presentation.features.recipes.add_update.domain.model.AddUpdateRecipeRequestModel
import com.courselara.receitafacil.ui.presentation.features.recipes.add_update.domain.repository.AddUpdateRecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface AddUpdateRecipeUseCase {
    operator fun invoke(parameters: Parameters): Flow<ResponseData<SimplesResponseModel>>
    data class Parameters(val addUpdateRecipeRequestModel: AddUpdateRecipeRequestModel)
}

class AddUpdateRecipeUseCaseImpl @Inject constructor(
    private val addUpdateRecipeRepository: AddUpdateRecipeRepository
) : AddUpdateRecipeUseCase, Task<AddUpdateRecipeUseCase.Parameters, SimplesResponseModel>() {

    override suspend fun executeTask(parameters: AddUpdateRecipeUseCase.Parameters): ResponseData<SimplesResponseModel> {
        return try {
            when (val response =
                addUpdateRecipeRepository.addRecipe(parameters.addUpdateRecipeRequestModel)) {
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
