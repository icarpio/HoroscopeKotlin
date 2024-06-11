package com.example.horoscope

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        //Datos pasados desde la primera actividad

        //Nombre traido del primer activity
        val dataName = intent.getStringExtra("Sign")

        val nameSecondTextView: TextView = findViewById(R.id.nameSecondTextView)
        nameSecondTextView.text = dataName

        //Descripcion traida del primer activity
        val dataDesc = intent.getStringExtra("Description")
        val DescSecondTextView: TextView = findViewById(R.id.DescSecondTextView)
        DescSecondTextView.text = dataDesc

        val imageResId = intent.getIntExtra("Logo", 0)
        val lsImageView: ImageView = findViewById(R.id.lsImageView)
        lsImageView.setImageResource(imageResId)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://horoscope-app-api.vercel.app/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(HoroscopeApiService::class.java)

        // Realizar la solicitud GET
        val call = service.getDailyHoroscope("cancer") // Cambia "aries" por el signo que necesites
        call.enqueue(object : Callback<HoroscopeResponse> {
            override fun onResponse(call: Call<HoroscopeResponse>, response: Response<HoroscopeResponse>) {
                if (response.isSuccessful) {
                    val horoscopeResponse = response.body()
                    if (horoscopeResponse != null) {
                        Log.i("Api Call", "Horoscope: ${horoscopeResponse.data.horoscope_data}")
                    } else {
                        Log.e("Api Call", "Response body is null")
                    }
                } else {
                    Log.e("Api Call", "Request failed with error code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<HoroscopeResponse>, t: Throwable) {
                // Manejar el error en caso de fallo en la solicitud
                Log.e("HoroscopeActivity", "Request failed: ${t.message}")
            }
        })







    }

}