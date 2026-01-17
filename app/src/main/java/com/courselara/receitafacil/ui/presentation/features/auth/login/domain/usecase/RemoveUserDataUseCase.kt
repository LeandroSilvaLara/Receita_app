package com.courselara.receitafacil.ui.presentation.features.auth.login.domain.usecase

import com.courselara.receitafacil.core.util.FlowTask
import com.courselara.receitafacil.core.util.ResponseData
import com.courselara.receitafacil.ui.presentation.features.auth.login.domain.repository.LoginRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface RemoveUserDataUseCase {
    suspend operator fun invoke(parameters: Unit = Unit): Flow<ResponseData<Unit>>
}

class RemoveUserDataUseCaseImpl @Inject constructor(
    private val loginRepository: LoginRepository
) : RemoveUserDataUseCase, FlowTask<Unit, ResponseData<Unit>>() {

    override suspend fun executeTakFlow(parameters: Unit): Flow<ResponseData<Unit>> {
        return flow {
            try {
                emit(ResponseData.Success(loginRepository.clearAll()))
            } catch (e: Throwable) {
                emit(ResponseData.Error(e))
            }
        }
    }
}

