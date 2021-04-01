package com.example.movieskritou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {

    private var _username: EditText? = null
    private var _password: EditText? = null
    private var _loginButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        _username = findViewById<EditText>(R.id.username)
        _password = findViewById<EditText>(R.id.password)
        _loginButton = findViewById<Button>(R.id.login)

        _loginButton!!.setOnClickListener {
            login()
        }
    }


    // Checking if the input in form is valid
    private fun validateInput(): Boolean {

        var valid = true

        val email = _username!!.text.toString()
        val password = _password!!.text.toString()

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
        if (email != "athtech") {
            _username!!.error = "Wrong username"
            valid = false
        }
        else {
            _username!!.error = null
        }

        if (password!= "123456") {
            _password!!.error = "Wrong password"
            valid = false
        }
        else {
            _password!!.error = null
        }

        return valid
    }

    private fun login() {

        if (!validateInput()) {
            onLoginFailed()
            return
        }
        else {
            onLoginSuccess()
        }
    }

    private fun onLoginSuccess() {
        finish()
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun onLoginFailed() {
        Toast.makeText(baseContext, "Login failed", Toast.LENGTH_LONG).show()
    }
}