package com.courselara.receitafacil.core.domain.model

import com.google.gson.annotations.SerializedName

/**
 * Represents a simplified response model typically used for basic API feedback.
 *
 * @property isSuccessFul Indicates whether the operation was completed successfully.
 * @property message A descriptive message providing details about the result or any errors encountered.
 */
data class SimplesResponseModel(
    val isSuccessFul : Boolean,
    val message : String,
)
