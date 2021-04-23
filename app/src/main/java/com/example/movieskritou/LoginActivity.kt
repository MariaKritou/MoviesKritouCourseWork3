package com.example.movieskritou

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.coroutines.launch
import org.json.JSONObject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class LoginActivity : AppCompatActivity() {

    private var _username: EditText? = null
    private var _password: EditText? = null
    private var _loginButton: Button? = null

    // CREDENTIALS FOR LOGIN
    private var username: String = "mariakritou"
    private var code: String = "athtech123"
    // CREDENTIALS FOR LOGIN END

    var token: String? = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        _username = findViewById<EditText>(R.id.username)
        _password = findViewById<EditText>(R.id.password)
        _loginButton = findViewById<Button>(R.id.login)

        _loginButton!!.setOnClickListener {

            val email = _username!!.text.toString()
            val password = _password!!.text.toString()
            login(email, password)
        }
    }


    // Checking if the input in form is valid
    private fun validateInput(email: String, password: String): Boolean {

        var valid = true

        //Empty EditViews
        if (email.isEmpty()) {
            _username!!.error = "Enter a valid username"
            valid = false
        } else {
            _username!!.error = null
        }

        if (password.isEmpty()) {
            _password!!.error = "Enter a valid password"
            valid = false
        } else {
            _password!!.error = null
        }

        //Wrong Credentials
        if (email != username) {
            _username!!.error = "Wrong username"
            valid = false
        } else {
            _username!!.error = null
        }

        if (password != code) {
            _password!!.error = "Wrong password"
            valid = false
        } else {
            _password!!.error = null
        }

        return valid
    }

    private fun login(email: String, password: String) {

        if (!validateInput(email, password)) {
            onLoginFailed()
            return
        } else {
            postToken(email, password)
        }
    }

    private fun postToken(email: String, password: String) {

        // Without lifecyclescope we would have to suspend all the functions
        lifecycleScope.launch {
            token = getData().getString("request_token")

            // I use string interpolation to pass the parameters uri
            var url =
                "https://api.themoviedb.org/3/authentication/token/validate_with_login?api_key=9460908ad9c98b66c0024f11d4bc9bae&username=${email}&password=${password}&request_token=${token}"

            val queue = Volley.newRequestQueue(applicationContext)

            Log.d("TOKEN", token.toString())

            // Volley post request
            val request = StringRequest(Request.Method.POST, url,
                Response.Listener { response ->
                    // Process the json
                    try {
                        onLoginSuccess()
                    } catch (e: Exception) {
                        onLoginFailed()
                    }

                }, Response.ErrorListener {
                    // Error in request
                    Log.d("ERROR", "Error in post request")

                })
            queue.add(request)
        }
    }

    // To make a post for login we need to create a session token every time we login cause it gets destroyed after 60mins (if not used)
    suspend fun getData() = suspendCoroutine<JSONObject> { cont ->
        val queue = Volley.newRequestQueue(this)

        val url =
            "https://api.themoviedb.org/3/authentication/token/new?api_key=9460908ad9c98b66c0024f11d4bc9bae"

        // To make it more synchronous we use suspend in order to use the response outside of the request and get the token in postToken()
        val stringRequest = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                cont.resume(response)
            },
            Response.ErrorListener { })

        queue.add(stringRequest)
    }

    private fun onLoginSuccess() {
        finish()
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun onLoginFailed() {
        Toast.makeText(baseContext, "Login failed", Toast.LENGTH_LONG).show()
    }
}