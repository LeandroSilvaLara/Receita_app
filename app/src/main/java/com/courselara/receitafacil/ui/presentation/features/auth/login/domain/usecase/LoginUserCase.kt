package com.courselara.receitafacil.ui.presentation.features.auth.login.domain.usecase

import coil.request.Parameters
import com.courselara.receitafacil.core.util.DispatcherProvider
import com.courselara.receitafacil.core.util.ResponseData
import com.courselara.receitafacil.core.util.ServiceResult
import com.courselara.receitafacil.core.util.Task
import com.courselara.receitafacil.ui.presentation.features.auth.login.domain.model.AuthUserRequestModel
import com.courselara.receitafacil.ui.presentation.features.auth.login.domain.model.TokenResponseModel
import com.courselara.receitafacil.ui.presentation.features.auth.login.domain.repository.LoginRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

interface LoginUserCase {
    operator fun invoke(parameters: Parameters): Flow<ResponseData<TokenResponseModel>>
    data class Parameters(val authUserRequestModel: AuthUserRequestModel)
}

class LoginUserCaseImpl @Inject constructor(
    private val loginRepository: LoginRepository
) : LoginUserCase, Task<LoginUserCase.Parameters, TokenResponseModel>() {
    override suspend fun executeTask(parameters: LoginUserCase.Parameters): ResponseData<TokenResponseModel> {
        return try {
            when (val response = loginRepository.login(parameters.authUserRequestModel)) {
                is ServiceResult.Success -> {
                    ResponseData.Success(response.data)
                }

                is ServiceResult.Error -> {
                    ResponseData.Error(Throwable(response.message))
                }
            }
        } catch (e: Exception) {
            ResponseData.Error(e)
        }

    }
}