package com.courselara.receitafacil.ui.presentation.features.auth.register.domain.usecase

import com.courselara.receitafacil.core.domain.model.SimplesResponseModel
import com.courselara.receitafacil.core.util.DispatcherProvider
import com.courselara.receitafacil.core.util.ResponseData
import com.courselara.receitafacil.core.util.ServiceResult
import com.courselara.receitafacil.core.util.Task
import com.courselara.receitafacil.ui.presentation.features.auth.register.domain.model.AddUserRequestModel
import com.courselara.receitafacil.ui.presentation.features.auth.register.domain.repository.RegisterUserRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

interface RegisterUserUserCase {

    operator fun invoke(parameters: Parameters): Flow<ResponseData<SimplesResponseModel>>
    data class Parameters(val addUserRequestModel: AddUserRequestModel)
}

class RegisterUserUserCaseImpl @Inject constructor(
    private val registerUserRepository: RegisterUserRepository,
    private val dispatcherProvider: DispatcherProvider
) : RegisterUserUserCase, Task<RegisterUserUserCase.Parameters, SimplesResponseModel>() {
    override suspend fun executeTask(parameters: RegisterUserUserCase.Parameters): ResponseData<SimplesResponseModel> {
        return try {
            withContext(dispatcherProvider.io()) {
                when (val response =
                    registerUserRepository.registerUser(parameters.addUserRequestModel)) {
                    is ServiceResult.Success -> {
                        ResponseData.Success(response.data)
                    }

                    is ServiceResult.Error -> {
                        ResponseData.Error(Throwable(response.message))
                    }
                }
            }

        } catch (e: Exception) {
            ResponseData.Error(e)
        }
    }
}
