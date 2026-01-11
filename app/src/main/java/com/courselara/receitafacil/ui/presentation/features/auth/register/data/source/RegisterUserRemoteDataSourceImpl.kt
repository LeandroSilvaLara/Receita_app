package com.courselara.receitafacil.ui.presentation.features.auth.register.data.source

import com.courselara.receitafacil.core.data.remote.RecipesServiceApi
import com.courselara.receitafacil.core.domain.exceptions.ErrorResponseException
import com.courselara.receitafacil.core.domain.model.SimplesResponseModel
import com.courselara.receitafacil.core.util.ServiceResult
import com.courselara.receitafacil.ui.presentation.features.auth.register.data.mapper.toAddUserRequest
import com.courselara.receitafacil.ui.presentation.features.auth.register.data.mapper.toSimplesResponseModel
import com.courselara.receitafacil.ui.presentation.features.auth.register.domain.model.AddUserRequestModel
import com.courselara.receitafacil.ui.presentation.features.auth.register.domain.source.RegisterUserRemoteDataSource
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import javax.inject.Inject

class RegisterUserRemoteDataSourceImpl @Inject constructor(
    private val recipesServiceAPI: RecipesServiceApi
) : RegisterUserRemoteDataSource {

    override suspend fun registerUser(adduserRequestModel: AddUserRequestModel): ServiceResult<SimplesResponseModel> {
        return try {

            val addUserRequest = adduserRequestModel.toAddUserRequest()
            val response = recipesServiceAPI.register(addUserRequest)
            if (response.isSuccessFul) {
                ServiceResult.Success(response.toSimplesResponseModel())
            } else {
                ServiceResult.Error(message = response.message)
            }

        } catch (e: RedirectResponseException) {
            ServiceResult.Error(e.response.status.value.toString(), e.response.status.description)
        }catch (e: ClientRequestException) {
            ServiceResult.Error(e.response.status.value.toString(), e.response.status.description)
        }catch (e: ServerResponseException) {
            ServiceResult.Error(e.response.status.value.toString(), e.response.status.description)
        }catch (e: ErrorResponseException) {
            ServiceResult.Error(code = e.error.httpCode.toString(), message = e.error.message)
        }catch (e: Exception) {
            ServiceResult.Error(message = e.message.toString())
        }

    }
}