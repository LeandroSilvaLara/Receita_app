package com.courselara.receitafacil.core.domain.model

/**
 * Represents the authentication and profile data of a user within the domain layer.
 *
 * @property token The authentication token used for authorized requests.
 * @property userName The display name or identifier of the user.
 * @property errorMessage An optional message detailing any errors encountered during data retrieval or authentication.
 */
data class UseData (
    val token: String = "",
    val userName: String = "",
    val errorMessage: String? = null
)