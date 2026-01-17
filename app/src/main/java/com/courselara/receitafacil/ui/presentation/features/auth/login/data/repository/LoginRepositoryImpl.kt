package com.courselara.receitafacil.ui.presentation.features.auth.login.data.repository

import com.courselara.receitafacil.core.data.local.datastore.DataStoreLocalDataSource
import com.courselara.receitafacil.core.domain.model.UseData
import com.courselara.receitafacil.core.util.ServiceResult
import com.courselara.receitafacil.ui.presentation.features.auth.login.domain.model.AuthUserRequestModel
import com.courselara.receitafacil.ui.presentation.features.auth.login.domain.model.TokenResponseModel
import com.courselara.receitafacil.ui.presentation.features.auth.login.domain.repository.LoginRepository
import com.courselara.receitafacil.ui.presentation.features.auth.login.domain.source.LoginRemoteDataSource
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class LoginRepositoryImpl @Inject constructor(
    private val remoteDataSource: LoginRemoteDataSource,
    private val localDataSource: DataStoreLocalDataSource
): LoginRepository {
    override suspend fun login(authUserRequestModel: AuthUserRequestModel): ServiceResult<TokenResponseModel> {
        return remoteDataSource.login(authUserRequestModel)
    }

    override fun getData(): Flow<UseData> = localDataSource.getData()

    override suspend fun saveData(token: String, userName: String) {
        return localDataSource.saveData(token, userName)
    }

    override suspend fun clearAll() = localDataSource.clearAll()

}