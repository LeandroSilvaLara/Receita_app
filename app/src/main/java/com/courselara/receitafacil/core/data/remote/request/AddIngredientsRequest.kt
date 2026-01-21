package com.courselara.receitafacil.core.data.remote.request

import com.google.gson.annotations.SerializedName

data class AddIngredientsRequest(
    @SerializedName("name")
    val name: String,
    @SerializedName("category")
    val category: String,

)
