package com.courselara.receitafacil.ui.presentation.features.recipes.list.domain.source

import com.courselara.receitafacil.core.domain.model.RecipesResponseModel
import com.courselara.receitafacil.core.util.ServiceResult

interface GetRecipesByUserRemoteDataSource {
    suspend fun getRecipesByUser(category: Int? = null) : ServiceResult<List<RecipesResponseModel>>
}