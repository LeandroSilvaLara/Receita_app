package com.courselara.receitafacil.ui.presentation.features.recipes.search.domain.usecases

import coil.request.Parameters
import com.courselara.receitafacil.core.domain.model.RecipesResponseModel
import com.courselara.receitafacil.core.util.ResponseData
import com.courselara.receitafacil.core.util.ServiceResult
import com.courselara.receitafacil.core.util.Task
import com.courselara.receitafacil.ui.presentation.features.recipes.search.domain.repository.SearchRecipesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface SearchRecipeUseCase {
    operator fun invoke(parameters: Parameters): Flow<ResponseData<List<RecipesResponseModel>>>
    data class Parameters(val query: String)
}

class SearchRecipeUseCaseImpl @Inject constructor(
    private val searchRecipesRepository: SearchRecipesRepository
): SearchRecipeUseCase, Task<SearchRecipeUseCase.Parameters, List<RecipesResponseModel>>() {
    override suspend fun executeTask(parameters: SearchRecipeUseCase.Parameters): ResponseData<List<RecipesResponseModel>> {

        return try {

            when(val serviceResult = searchRecipesRepository.search(parameters.query)) {
                is ServiceResult.Success -> {
                    if (serviceResult.data.isEmpty()){
                        ResponseData.Empty
                    }else{
                        ResponseData.Success(serviceResult.data)
                    }
                }
                is ServiceResult.Error -> {
                    ResponseData.Error(Throwable(serviceResult.message))
                }
            }
        } catch (e: Exception) {
            return ResponseData.Error(e)
        }
    }

}