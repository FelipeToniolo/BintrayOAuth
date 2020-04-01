package br.ftoniolo.bintrayoauth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.ftoniolo.braspagoauth.BraspagOAuth
import br.ftoniolo.braspagoauth.services.TokenCallback
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val clientId = "SEU CLIENT ID"
        val clientSecret = "SEU CLIENT SECRET"

        val braspagOAuth = BraspagOAuth(clientId, clientSecret)

        braspagOAuth.generateToken(object :
            TokenCallback {
            override fun onGetToken(token: String) {
                txt_token.text = token
            }

            override fun onError(error: String) {
                txt_token.text = "Deu ruim $error"
            }
        })
    }
}
