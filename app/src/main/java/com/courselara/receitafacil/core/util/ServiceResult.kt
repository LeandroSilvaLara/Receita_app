package com.courselara.receitafacil.core.util

sealed class ServiceResult<out T>{
    data class Success<out T>(val data: T) : ServiceResult<T>()
    data class Error(val code: String? = null, val message: String) : ServiceResult<Nothing>()
}