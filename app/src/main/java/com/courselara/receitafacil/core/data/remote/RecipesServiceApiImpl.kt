package com.courselara.receitafacil.core.data.remote

import com.courselara.receitafacil.core.data.remote.request.AddUpdateRecipeRequest
import com.courselara.receitafacil.core.data.remote.request.AddUserRequest
import com.courselara.receitafacil.core.data.remote.request.AuthUserRequest
import com.courselara.receitafacil.core.data.remote.responses.RecipeDetailsResponse
import com.courselara.receitafacil.core.data.remote.responses.RecipesResponse
import com.courselara.receitafacil.core.data.remote.responses.SimplesResponse
import com.courselara.receitafacil.core.data.remote.responses.TokenResponse
import com.courselara.receitafacil.core.data.remote.responses.UserResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject

class RecipesServiceApiImpl @Inject constructor(
    private val client: HttpClient
) : RecipesServiceApi {
    override suspend fun login(authUserRequest: AuthUserRequest): TokenResponse {
        val response = client.post("users/login"){
            contentType(ContentType.Application.Json)
            setBody(authUserRequest)
        }
        return response.body()
    }

    override suspend fun register(addUserRequest: AddUserRequest): SimplesResponse {
        val response = client.post("users/register"){
            contentType(ContentType.Application.Json)
            setBody(addUserRequest)
        }
        return response.body()
    }

    override suspend fun getProfileUser(): UserResponse {
        val response = client.get("users/profile"){
            contentType(ContentType.Application.Json)
        }
        return response.body()
    }

    override suspend fun getRecipesByUser(category: Int?): List<RecipesResponse> {
        val response: HttpResponse = client.get("recipes") {
            contentType(ContentType.Application.Json)
            category?.let { parameter("category", it) }
        }
        return response.body()
    }

    override suspend fun searchRecipes(nameOrIngredients: String): List<RecipesResponse> {
        val response: HttpResponse = client.get("recipes/search") {
            contentType(ContentType.Application.Json)
            parameter("nameOrIngredients", nameOrIngredients)
        }
        return response.body()
    }

    override suspend fun getRecipeById(recipeId: String): RecipeDetailsResponse {
        val response: HttpResponse = client.get("recipes/${recipeId}") {
            contentType(ContentType.Application.Json)
        }
        return response.body()
    }

    override suspend fun addRecipe(addUpdateRecipeRequest: AddUpdateRecipeRequest): SimplesResponse {
        val response: HttpResponse = client.post("recipes") {
            contentType(ContentType.Application.Json)
            setBody(addUpdateRecipeRequest)
        }
        return response.body()
    }

    override suspend fun updateRecipe(
        recipeId: String,
        addUpdateRecipeRequest: AddUpdateRecipeRequest
    ): SimplesResponse {
        val response: HttpResponse = client.put("recipes/${recipeId}") {
            contentType(ContentType.Application.Json)
            setBody(addUpdateRecipeRequest)
        }
        return response.body()
    }

    override suspend fun deleteRecipe(recipeId: String): SimplesResponse {
        val response: HttpResponse = client.delete("recipes/${recipeId}") {
            contentType(ContentType.Application.Json)
        }
        return response.body()
    }
}