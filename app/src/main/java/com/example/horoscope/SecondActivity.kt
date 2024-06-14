package com.example.horoscope

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
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

    lateinit var horoscope: Horoscope

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        //Datos pasados desde la primera actividad


        val id: String = intent.getStringExtra(EXTRA_HOROSCOPE_ID)!!
        horoscope  = HoroscopeProvider.findById(id)!!

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
            show(horoscope.id)
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_activity_second, menu)
        return true
    }
    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_share -> {
                // Acción para opción 1
                true
            }
            R.id.action_favourite -> {
                // Acción para opción 2
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }


    private fun showError(errorApi: String) {
        Toast.makeText(this, errorApi, Toast.LENGTH_SHORT).show()
    }

    //Llamada a la API
    private suspend fun show(id: String) {
        val response = RetrofitInstance.api.getDailyHoroscope(sign = id)
        if (response.isSuccessful) {
            val horoscopeResponse = response.body()
            if (horoscopeResponse != null && horoscopeResponse.success) {
                val data = horoscopeResponse.data
                findViewById<TextView>(R.id.bodyTextView).text = data.horoscope_data
                findViewById<TextView>(R.id.dateTextView).text = data.date
                println("${data.date}: ${data.horoscope_data}")
            } else {
                showError("Response was not successful or data is null")
            }
        } else {
            showError("Error: ${response.errorBody()?.string()}")
        }
    }


}









