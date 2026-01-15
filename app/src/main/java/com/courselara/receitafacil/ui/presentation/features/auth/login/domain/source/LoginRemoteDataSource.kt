package com.courselara.receitafacil.ui.presentation.features.auth.login.domain.source

import com.courselara.receitafacil.core.util.ServiceResult
import com.courselara.receitafacil.ui.presentation.features.auth.login.domain.model.AuthUserRequestModel
import com.courselara.receitafacil.ui.presentation.features.auth.login.domain.model.TokenResponseModel

interface LoginRemoteDataSource {
    suspend fun login(authUserRequestModel: AuthUserRequestModel) : ServiceResult<TokenResponseModel>
}