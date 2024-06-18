package com.example.horoscope.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.horoscope.R
import com.example.horoscope.utils.SessionManager

class LoginActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Verificar si el usuario ya está logueado
        /* val username = getCurrentUser()
        if (username != null) {
            navigateToMainActivity()
            return
        }*/




        val loginButton: Button = findViewById(R.id.loginButton)
        loginButton.setOnClickListener {
            val userNameEditText = findViewById<EditText>(R.id.usernameEditText)
            val username = userNameEditText.text.toString()
            if (username.isEmpty()) {
                userNameEditText.error = "Please enter a username!"
                userNameEditText.requestFocus()
            } else {
                login(username)
            }
        }
    }

    private fun login(username: String) {
        val sharedPreferences = getSharedPreferences(SessionManager.SESSION_KEY, Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString("username", username)
            apply()
        }
        /*
        val editor = sharedPreferences?.edit()
        if(editor != null) {
            editor.putString("username", username)
            editor.apply()
        } */
        navigateToMainActivity()
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun getCurrentUser(): String? {
        val sharedPreferences = getSharedPreferences(SessionManager.SESSION_KEY, Context.MODE_PRIVATE)
        return sharedPreferences.getString("username", null)
    }
}