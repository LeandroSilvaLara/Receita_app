package com.courselara.receitafacil.ui.presentation.features.recipes.list.domain.repository

import com.courselara.receitafacil.core.domain.model.RecipesResponseModel
import com.courselara.receitafacil.core.util.ServiceResult

interface GetRecipesByUserRepository{
    suspend fun getRecipesByUser(category: Int? = null) : ServiceResult<List<RecipesResponseModel>>
}