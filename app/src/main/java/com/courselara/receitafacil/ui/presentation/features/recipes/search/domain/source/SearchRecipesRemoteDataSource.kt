package com.courselara.receitafacil.ui.presentation.features.recipes.search.domain.source

import com.courselara.receitafacil.core.domain.model.RecipesResponseModel
import com.courselara.receitafacil.core.util.ServiceResult

interface SearchRecipesRemoteDataSource {
    suspend fun search(nameOrIngredient: String): ServiceResult<List<RecipesResponseModel>>
}