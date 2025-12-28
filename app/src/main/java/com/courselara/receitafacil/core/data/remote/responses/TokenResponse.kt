package com.courselara.receitafacil.core.data.remote.responses

import com.google.gson.annotations.SerializedName

data class TokenResponse (
    @SerializedName("isSuccessFul") val isSuccessFul : Boolean,
    @SerializedName("message") val message : String,
    @SerializedName("token") val token : String,
    @SerializedName("userName") val userName : String,

)