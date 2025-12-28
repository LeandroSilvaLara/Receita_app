package com.courselara.receitafacil.core.data.remote.responses

import com.google.gson.annotations.SerializedName

data class SimplesResponse (
    @SerializedName("isSuccessFul") val isSuccessFul : Boolean,
    @SerializedName("message") val message : String,

    )