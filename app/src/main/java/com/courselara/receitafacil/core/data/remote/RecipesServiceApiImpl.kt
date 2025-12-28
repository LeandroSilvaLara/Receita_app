package com.courselara.receitafacil.core.data.remote

import com.courselara.receitafacil.core.data.remote.request.AddUserRequest
import com.courselara.receitafacil.core.data.remote.responses.SimplesResponse
import com.courselara.receitafacil.core.data.remote.responses.TokenResponse
import com.courselara.receitafacil.core.data.remote.responses.UserResponse

class RecipesServiceApiImpl constructor() : RecipesServiceApi {
    override suspend fun login(authUserRequest: AddUserRequest): TokenResponse {
        TODO("Not yet implemented")
    }

    override suspend fun register(addUserRequest: AddUserRequest): SimplesResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getProfileUser(): UserResponse {
        TODO("Not yet implemented")
    }
}