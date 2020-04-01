package br.ftoniolo.braspagoauth.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AuthClientModel(
    @SerializedName("access_token")
    @Expose
    val token: String,

    @SerializedName("token _type")
    @Expose
    val tokenType: String,

    @SerializedName("expire_in")
    @Expose
    val expireIn: Int
)

