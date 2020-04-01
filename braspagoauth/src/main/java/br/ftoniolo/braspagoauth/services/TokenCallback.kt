package br.ftoniolo.braspagoauth.services

interface TokenCallback {
    fun onGetToken(token: String)
    fun onError(error: String)
}