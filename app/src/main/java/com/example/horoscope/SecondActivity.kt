package com.example.horoscope

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        // Obtén los datos pasados desde la primera actividad
        // Configura la vista según los datos recibidos
        // Por ejemplo, muestra el dato en un TextView
        //Nombre traido del primer activity
        val dataName = intent.getStringExtra("Sign")

        val nameSecondTextView: TextView = findViewById(R.id.nameSecondTextView)
        nameSecondTextView.text = dataName

        //Descripcion traida del primer activity
        val dataDesc = intent.getStringExtra("Description")
        val DescSecondTextView: TextView = findViewById(R.id.DescSecondTextView)
        DescSecondTextView.text = dataDesc

        val imageResId = intent.getIntExtra("Logo",0)
        val lsImageView: ImageView = findViewById(R.id.lsImageView)
        lsImageView.setImageResource(imageResId)
        Log.i("imageResId", imageResId.toString())
    }
}