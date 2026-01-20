package com.courselara.receitafacil.ui.presentation.features.auth.register.data.repository

import com.courselara.receitafacil.core.domain.model.SimplesResponseModel
import com.courselara.receitafacil.core.util.DispatcherProvider
import com.courselara.receitafacil.core.util.ServiceResult
import com.courselara.receitafacil.ui.presentation.features.auth.register.domain.model.AddUserRequestModel
import com.courselara.receitafacil.ui.presentation.features.auth.register.domain.repository.RegisterUserRepository
import com.courselara.receitafacil.ui.presentation.features.auth.register.domain.source.RegisterUserRemoteDataSource
import jakarta.inject.Inject
import kotlinx.coroutines.withContext

class RegisterUserRepositoryImpl @Inject constructor(
    private val remoteDataSource: RegisterUserRemoteDataSource,
    private val dispatcherProvider: DispatcherProvider
) : RegisterUserRepository {

    override suspend fun registerUser(adduserRequestModel: AddUserRequestModel): ServiceResult<SimplesResponseModel> {
        return withContext(dispatcherProvider.io()) {
            remoteDataSource.registerUser(adduserRequestModel)
        }
    }
}
