package com.example.horoscope

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.sign

class SecondActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_HOROSCOPE_ID = "HOROSCOPE_ID"
    }
    lateinit var horoscope:Horoscope
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        //Datos pasados desde la primera actividad

        val id = intent.getStringExtra(EXTRA_HOROSCOPE_ID)
        horoscope = HoroscopeProvider.findById(id!!)!!

        findViewById<TextView>(R.id.nameSecondTextView).setText(horoscope.name)
        findViewById<TextView>(R.id.DescSecondTextView).setText(horoscope.description)
        findViewById<ImageView>(R.id.lsImageView).setImageResource(horoscope.logo)


        //Boton Atras
        val backButton: Button = findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Llamar a show() desde un contexto suspendido
        lifecycleScope.launch {
            show("aries")
        }
    }
    private fun showError(errorApi: String) {
        Toast.makeText(this, errorApi, Toast.LENGTH_SHORT).show()
    }

    private suspend fun show(id:String){
        val sign = id  // Cambiar esto por cualquier signo
        val response = RetrofitInstance.api.getDailyHoroscope(sign = sign)

        if (response.isSuccessful) {
            val horoscopeResponse = response.body()
            if (horoscopeResponse != null && horoscopeResponse.success) {
                val data = horoscopeResponse.data
                println("Horoscope for ${data.date}: ${data.horoscope_data}")
            } else {
                println("Response was not successful or data is null")
            }
        } else {
            println("Error: ${response.errorBody()?.string()}")
        }
    }

    }









