package br.ftoniolo.braspagoauth.network

import br.ftoniolo.braspagoauth.services.OAuthService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInit {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://meucheckoutsandbox.braspag.com.br/api/public/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun encapsulateOAuthService(): OAuthService {
        return retrofit.create(OAuthService::class.java)
    }
}