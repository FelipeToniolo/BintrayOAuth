package br.ftoniolo.braspagoauth

import android.util.Log
import br.ftoniolo.braspagoauth.extension.toStatusCode
import br.ftoniolo.braspagoauth.models.AccessToken
import br.ftoniolo.braspagoauth.models.AuthClientModel
import br.ftoniolo.braspagoauth.network.RetrofitInit
import br.ftoniolo.braspagoauth.services.TokenCallback
import okhttp3.Credentials
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class BraspagOAuth(
    private val clientId: String,
    private val clientSecret: String
) {

    fun generateToken(
        callbacks: TokenCallback
    ) {
        getToken({ accessToken ->
            callbacks.onGetToken(accessToken.token)
        }, callbacks::onError)
    }

    private fun getToken(
        onSuccessCallback: (model: AccessToken) -> Unit,
        onError: (error: String) -> Unit
    ) {
        val authorization = Credentials.basic(clientId, clientSecret)

        val call =
            RetrofitInit().encapsulateOAuthService()
                .getTokenOAuth(authorization, "client_credentials")

        call.enqueue(object : Callback<AuthClientModel> {

            override fun onResponse(
                call: Call<AuthClientModel>,
                response: Response<AuthClientModel>
            ) {
                Log.i("onResponse", "requisicao com sucesso")
                if (response.isSuccessful) {
                    response.body()?.apply {
                        onSuccessCallback(
                            AccessToken(
                                token,
                                expireIn,
                                Calendar.getInstance().time
                            )
                        )
                    }
                    if (response.body() == null)
                        onError.invoke("The response object is null.")
                } else {
                    onError.invoke("error ${response.code()} ${response.code().toStatusCode()}")
                }
            }

            override fun onFailure(call: Call<AuthClientModel>, t: Throwable) {
                Log.e("onFailure", "requisicao falhou")
                t.message?.let { onError.invoke(it) }
            }
        })
    }
}