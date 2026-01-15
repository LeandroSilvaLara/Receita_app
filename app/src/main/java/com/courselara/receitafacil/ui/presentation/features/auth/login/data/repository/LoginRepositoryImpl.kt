package com.courselara.receitafacil.ui.presentation.features.auth.login.data.repository

import com.courselara.receitafacil.core.util.ServiceResult
import com.courselara.receitafacil.ui.presentation.features.auth.login.domain.model.AuthUserRequestModel
import com.courselara.receitafacil.ui.presentation.features.auth.login.domain.model.TokenResponseModel
import com.courselara.receitafacil.ui.presentation.features.auth.login.domain.repository.LoginRepository
import com.courselara.receitafacil.ui.presentation.features.auth.login.domain.source.LoginRemoteDataSource
import jakarta.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val remoteDataSource: LoginRemoteDataSource
): LoginRepository {
    override suspend fun login(authUserRequestModel: AuthUserRequestModel): ServiceResult<TokenResponseModel> {
        return remoteDataSource.login(authUserRequestModel)
    }

}