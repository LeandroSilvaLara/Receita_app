package com.courselara.receitafacil.core.data.remote.responses

import com.google.gson.annotations.SerializedName

data class IngredientsResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("quantity")
    val category: String,
)
