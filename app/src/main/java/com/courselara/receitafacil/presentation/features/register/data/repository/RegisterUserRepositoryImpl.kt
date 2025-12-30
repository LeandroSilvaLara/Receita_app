package com.courselara.receitafacil.presentation.features.register.data.repository

import com.courselara.receitafacil.core.domain.model.SimplesResponseModel
import com.courselara.receitafacil.core.util.ServiceResult
import com.courselara.receitafacil.presentation.features.register.domain.model.AddUserRequestModel
import com.courselara.receitafacil.presentation.features.register.domain.repository.RegisterUserRepository
import com.courselara.receitafacil.presentation.features.register.domain.source.RegisterUserRemoteDataSource
import jakarta.inject.Inject

class RegisterUserRepositoryImpl @Inject constructor(
    private val remoteDataSource: RegisterUserRemoteDataSource
) : RegisterUserRepository {

    override suspend fun registerUser(adduserRequestModel: AddUserRequestModel): ServiceResult<SimplesResponseModel> {
        return remoteDataSource.registerUser(adduserRequestModel)
    }
}