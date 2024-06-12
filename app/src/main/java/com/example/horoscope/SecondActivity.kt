package com.example.horoscope

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
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

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        //Datos pasados desde la primera actividad

        //Nombre traido del primer activity
        val dataName = intent.getStringExtra("Sign")
        val nameSecondTextView = findViewById<TextView>(R.id.nameSecondTextView)
        nameSecondTextView.text = dataName

        //Descripcion traida del primer activity
        val dataDesc = intent.getStringExtra("Description")
        val DescSecondTextView = findViewById<TextView>(R.id.DescSecondTextView)
        DescSecondTextView.text = dataDesc

        val imageResId = intent.getIntExtra("Logo", 0)
        val lsImageView: ImageView = findViewById(R.id.lsImageView)
        lsImageView.setImageResource(imageResId)

        val dataId = intent.getStringExtra("id")

        val backButton: Button = findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        if (dataId != null) {
            getMessage(dataId)
        }

    }

    private fun showError(errorApi: String) {
        Toast.makeText(this, errorApi, Toast.LENGTH_SHORT).show()
    }

    private fun getMessage(query: String) {
            //Retrofit
            val retrofit = Retrofit.Builder()
                .baseUrl("https://horoscope-app-api.vercel.app/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val service = retrofit.create(HoroscopeApiService::class.java)

            val call = service.getDailyHoroscope(query) // Cambia "aries" por el signo que necesites
            call.enqueue(object : Callback<HoroscopeResponse> {
                override fun onResponse(
                    call: Call<HoroscopeResponse>,
                    response: Response<HoroscopeResponse>
                ) {
                    if (response.isSuccessful) {
                        val horoscopeResponse = response.body()
                        if (horoscopeResponse != null) {
                            val bodyTextView = findViewById<TextView>(R.id.bodyTextView)
                            bodyTextView.text = horoscopeResponse.data.horoscope_data
                            Log.i("Api Call", "Horoscope: ${horoscopeResponse.data.horoscope_data}")
                        } else {
                            showError("Response body is null")
                            Log.e("Api Call", "Response body is null")
                        }
                    } else {
                        showError("Ha ocurrido un error en la llamda a la API ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<HoroscopeResponse>, t: Throwable) {
                    showError("Ha ocurrido un error ${t.message}")
                }
            })



    }






}


