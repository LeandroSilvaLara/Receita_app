package com.courselara.receitafacil.core.data.remote

import com.courselara.receitafacil.core.data.remote.request.AddUpdateRecipeRequest
import com.courselara.receitafacil.core.data.remote.request.AddUserRequest
import com.courselara.receitafacil.core.data.remote.request.AuthUserRequest
import com.courselara.receitafacil.core.data.remote.responses.RecipeDetailsResponse
import com.courselara.receitafacil.core.data.remote.responses.RecipesResponse
import com.courselara.receitafacil.core.data.remote.responses.SimplesResponse
import com.courselara.receitafacil.core.data.remote.responses.TokenResponse
import com.courselara.receitafacil.core.data.remote.responses.UserResponse

interface RecipesServiceApi {
    suspend fun login(authUserRequest: AuthUserRequest) : TokenResponse
    suspend fun register(addUserRequest: AddUserRequest) : SimplesResponse
    suspend fun getProfileUser() : UserResponse

    suspend fun getRecipesByUser(category: Int?): List<RecipesResponse>
    suspend fun searchRecipes(nameOrIngredients: String): List<RecipesResponse>
    suspend fun getRecipeById(recipeId: String): RecipeDetailsResponse
    suspend fun addRecipe(addUpdateRecipeRequest: AddUpdateRecipeRequest) : SimplesResponse
    suspend fun updateRecipe(recipeId: String, addUpdateRecipeRequest: AddUpdateRecipeRequest) : SimplesResponse
    suspend fun deleteRecipe(recipeId: String) : SimplesResponse

}