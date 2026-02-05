package com.courselara.receitafacil.ui.presentation.features.recipes.detail.domain.source

import com.courselara.receitafacil.core.domain.model.RecipeDetailModel
import com.courselara.receitafacil.core.domain.model.SimplesResponseModel
import com.courselara.receitafacil.core.util.ServiceResult

interface RecipeDetailRemoteDataSource {
    suspend fun getRecipeById(recipeId: String): ServiceResult<RecipeDetailModel>
    suspend fun deleteRecipe(recipeId: String): ServiceResult<SimplesResponseModel>
}

