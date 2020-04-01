package br.ftoniolo.braspagoauth.models

import java.util.*

data class AccessToken(
    val token: String,
    val expiresIn: Int,
    val issuedAt: Date
)
