package com.courselara.receitafacil.ui.presentation.features.auth.login.domain.usecase

import com.courselara.receitafacil.core.util.DispatcherProvider
import com.courselara.receitafacil.core.util.ResponseData
import com.courselara.receitafacil.core.util.Task
import com.courselara.receitafacil.ui.presentation.features.auth.login.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface SaveUserDataUseCase {
    operator fun invoke(parameters: Parameters): Flow<ResponseData<Unit>>
    data class Parameters(val token: String, val userName: String)
}

class SaveUserDataUseCaseImpl @Inject constructor(
    private val loginRepository: LoginRepository,
    private val dispatcherProvider: DispatcherProvider
) : SaveUserDataUseCase, Task<SaveUserDataUseCase.Parameters, Unit>() {

    override suspend fun executeTask(parameters: SaveUserDataUseCase.Parameters): ResponseData<Unit> {
        return try {
            withContext(dispatcherProvider.io()) {
                ResponseData.Success(
                    loginRepository.saveData(
                        parameters.token, parameters.userName
                    )
                )
            }
        } catch (e: Throwable) {
            ResponseData.Error(e)
        }
    }

}