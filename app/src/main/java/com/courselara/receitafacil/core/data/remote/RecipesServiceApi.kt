package com.courselara.receitafacil.core.data.remote

import com.courselara.receitafacil.core.data.remote.request.AddUserRequest
import com.courselara.receitafacil.core.data.remote.request.AuthUserRequest
import com.courselara.receitafacil.core.data.remote.responses.SimplesResponse
import com.courselara.receitafacil.core.data.remote.responses.TokenResponse
import com.courselara.receitafacil.core.data.remote.responses.UserResponse

interface RecipesServiceApi {
    suspend fun login(authUserRequest: AuthUserRequest) : TokenResponse
    suspend fun register(addUserRequest: AddUserRequest) : SimplesResponse
    suspend fun getProfileUser() : UserResponse
}