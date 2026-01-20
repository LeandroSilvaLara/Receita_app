package com.courselara.receitafacil.ui.presentation.features.auth.login.data.repository

import com.courselara.receitafacil.core.data.local.datastore.DataStoreLocalDataSource
import com.courselara.receitafacil.core.domain.model.UseData
import com.courselara.receitafacil.core.util.DispatcherProvider
import com.courselara.receitafacil.core.util.ServiceResult
import com.courselara.receitafacil.ui.presentation.features.auth.login.domain.model.AuthUserRequestModel
import com.courselara.receitafacil.ui.presentation.features.auth.login.domain.model.TokenResponseModel
import com.courselara.receitafacil.ui.presentation.features.auth.login.domain.repository.LoginRepository
import com.courselara.receitafacil.ui.presentation.features.auth.login.domain.source.LoginRemoteDataSource
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class LoginRepositoryImpl @Inject constructor(
    private val remoteDataSource: LoginRemoteDataSource,
    private val localDataSource: DataStoreLocalDataSource,
    private val dispatcherProvider: DispatcherProvider
) : LoginRepository {
    override suspend fun login(authUserRequestModel: AuthUserRequestModel): ServiceResult<TokenResponseModel> {
        return withContext(dispatcherProvider.io()) {
            remoteDataSource.login(authUserRequestModel)
        }
    }

    override fun getData(): Flow<UseData> = localDataSource.getData()

    override suspend fun saveData(token: String, userName: String) {
        return withContext(dispatcherProvider.io()) {
            localDataSource.saveData(token, userName)
        }
    }

    override suspend fun clearAll() {
        return withContext(dispatcherProvider.io()) {
            localDataSource.clearAll()
        }
    }
}