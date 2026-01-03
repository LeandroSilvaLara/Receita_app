package com.courselara.receitafacil.ui.presentation.features.register.data.mapper

import com.courselara.receitafacil.core.data.remote.request.AddUserRequest
import com.courselara.receitafacil.core.data.remote.responses.SimplesResponse
import com.courselara.receitafacil.core.domain.model.SimplesResponseModel
import com.courselara.receitafacil.ui.presentation.features.register.domain.model.AddUserRequestModel

fun AddUserRequestModel.toAddUserRequest(): AddUserRequest {
    return AddUserRequest(name, email, password, phone)
}

fun SimplesResponse.toSimplesResponseModel(): SimplesResponseModel {
    return SimplesResponseModel(isSuccessFul, message)
}