package com.courselara.receitafacil.ui.presentation.features.auth.login.domain.model

data class TokenResponseModel (
    val isSuccessFul: Boolean,
    val message: String? = null,
    val token: String? = null,
    val userNamer: String? = null

)