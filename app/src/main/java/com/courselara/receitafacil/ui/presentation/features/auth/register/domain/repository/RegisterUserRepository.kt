package com.courselara.receitafacil.ui.presentation.features.auth.register.domain.repository

import com.courselara.receitafacil.core.domain.model.SimplesResponseModel
import com.courselara.receitafacil.core.util.ServiceResult
import com.courselara.receitafacil.ui.presentation.features.auth.register.domain.model.AddUserRequestModel

interface RegisterUserRepository {
    suspend fun registerUser(adduserRequestModel: AddUserRequestModel): ServiceResult<SimplesResponseModel>
}


