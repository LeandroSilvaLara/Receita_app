package com.courselara.receitafacil.ui.presentation.features.auth.login.domain.repository

import com.courselara.receitafacil.core.domain.model.UseData
import com.courselara.receitafacil.core.util.ServiceResult
import com.courselara.receitafacil.ui.presentation.features.auth.login.domain.model.AuthUserRequestModel
import com.courselara.receitafacil.ui.presentation.features.auth.login.domain.model.TokenResponseModel
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun login(authUserRequestModel: AuthUserRequestModel): ServiceResult <TokenResponseModel>
    fun getData(): Flow<UseData>
    suspend fun saveData(token: String, userName: String)
    suspend fun clearAll()
}