package com.courselara.receitafacil.ui.presentation.features.auth.login.domain.repository

import com.courselara.receitafacil.core.util.ServiceResult
import com.courselara.receitafacil.ui.presentation.features.auth.login.domain.model.AuthUserRequestModel
import com.courselara.receitafacil.ui.presentation.features.auth.login.domain.model.TokenResponseModel

interface LoginRepository {
    suspend fun login(authUserRequestModel: AuthUserRequestModel): ServiceResult <TokenResponseModel>
}