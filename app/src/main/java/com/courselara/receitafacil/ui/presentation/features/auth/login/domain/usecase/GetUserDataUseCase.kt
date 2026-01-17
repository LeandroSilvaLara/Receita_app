package com.courselara.receitafacil.ui.presentation.features.auth.login.domain.usecase

import com.courselara.receitafacil.core.domain.model.UseData
import com.courselara.receitafacil.core.util.FlowTask
import com.courselara.receitafacil.ui.presentation.features.auth.login.domain.repository.LoginRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.take

interface GetUserDataUseCase {
    suspend operator fun invoke(parameters: Unit = Unit): Flow<UseData>
}

class GetUserDataUseCaseImpl @Inject constructor(
    private val loginRepository: LoginRepository
) : GetUserDataUseCase, FlowTask<Unit, UseData>() {

    override suspend fun executeTakFlow(parameters: Unit): Flow<UseData> {
        return loginRepository.getData()
            .catch { error ->
                emit(
                    UseData(
                        errorMessage = error.message ?: "Erro desconhecido"
                    )
                )
            }.take(1)
    }

}