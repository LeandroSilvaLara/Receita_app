package com.courselara.receitafacil.ui.presentation.features.auth.login.data.source

import com.courselara.receitafacil.core.data.remote.RecipesServiceApi
import com.courselara.receitafacil.core.domain.exceptions.ErrorResponseException
import com.courselara.receitafacil.core.util.ServiceResult
import com.courselara.receitafacil.ui.presentation.features.auth.login.data.mappers.toAuthUserRequest
import com.courselara.receitafacil.ui.presentation.features.auth.login.data.mappers.toTokenResponseModel
import com.courselara.receitafacil.ui.presentation.features.auth.login.domain.model.AuthUserRequestModel
import com.courselara.receitafacil.ui.presentation.features.auth.login.domain.model.TokenResponseModel
import com.courselara.receitafacil.ui.presentation.features.auth.login.domain.source.LoginRemoteDataSource
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import javax.inject.Inject

class LoginRemoteDataSourceImpl @Inject constructor(
    private val recipesServiceApi: RecipesServiceApi
) : LoginRemoteDataSource {
    override suspend fun login(authUserRequestModel: AuthUserRequestModel): ServiceResult<TokenResponseModel> {
        return try {

            val response = recipesServiceApi.login(authUserRequestModel.toAuthUserRequest())
            if (response.isSuccessFul) {
                ServiceResult.Success(response.toTokenResponseModel())
            } else {
                ServiceResult.Error(message = response.message)
            }


        } catch (e: RedirectResponseException) {
            ServiceResult.Error(e.response.status.value.toString(), e.response.status.description)
        } catch (e: ClientRequestException) {
            ServiceResult.Error(e.response.status.value.toString(), e.response.status.description)
        } catch (e: ServerResponseException) {
            ServiceResult.Error(e.response.status.value.toString(), e.response.status.description)
        } catch (e: ErrorResponseException) {
            ServiceResult.Error(code = e.error.httpCode.toString(), message = e.error.message)
        } catch (e: Exception) {
            ServiceResult.Error(message = e.message.toString())
        }
    }
}