package com.courselara.receitafacil.ui.presentation.features.auth.login.data.mappers

import com.courselara.receitafacil.core.data.remote.request.AuthUserRequest
import com.courselara.receitafacil.core.data.remote.responses.TokenResponse
import com.courselara.receitafacil.ui.presentation.features.auth.login.domain.model.AuthUserRequestModel
import com.courselara.receitafacil.ui.presentation.features.auth.login.domain.model.TokenResponseModel

fun AuthUserRequestModel.toAuthUserRequest(): AuthUserRequest {
    return AuthUserRequest(
        email = email,
        password = password
    )
}

fun TokenResponse.toTokenResponseModel(): TokenResponseModel {
    return TokenResponseModel(
        isSuccessFul,
        message,
        token,
        userName
    )

}