package com.courselara.receitafacil.ui.presentation.features.recipes.search.domain.repository

import com.courselara.receitafacil.core.domain.model.RecipesResponseModel
import com.courselara.receitafacil.core.util.ServiceResult

interface SearchRecipesRepository {
    suspend fun search(nameOrIngredient: String): ServiceResult<List<RecipesResponseModel>>
}
