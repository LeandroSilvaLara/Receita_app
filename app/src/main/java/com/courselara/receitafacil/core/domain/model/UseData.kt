package com.courselara.receitafacil.core.domain.model

data class UseData (
    val token: String,
    val userName: String,
    val errorMessage: String? = null
)