package com.example.horoscope

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val horoscopeList: List<Horoscope> = listOf(
        Horoscope("Aries", R.string.horoscope_name_aries, R.string.horoscope_date_aries,R.drawable.aries_icon),
        Horoscope("aries", R.string.horoscope_name_taurus, R.string.horoscope_date_taurus, 0),
        Horoscope("aries", R.string.horoscope_name_gemini, R.string.horoscope_date_gemini,0),
        Horoscope("aries", R.string.horoscope_name_cancer, R.string.horoscope_date_cancer, 0),
        Horoscope("aries", R.string.horoscope_name_leo, R.string.horoscope_date_leo,0),
        Horoscope("aries", R.string.horoscope_name_virgo, R.string.horoscope_date_virgo,0),
        Horoscope("aries", R.string.horoscope_name_libra, R.string.horoscope_date_libra, 0),
        Horoscope("aries", R.string.horoscope_name_scorpio, R.string.horoscope_date_scorpio, 0),
        Horoscope("aries", R.string.horoscope_name_sagittarius, R.string.horoscope_date_sagittarius,0),
        Horoscope("aries", R.string.horoscope_name_capricorn, R.string.horoscope_date_capricorn, 0),
        Horoscope("aries", R.string.horoscope_name_aquarius, R.string.horoscope_date_aquarius, R.drawable.aquarius_icon),
        Horoscope("aries", R.string.horoscope_name_pisces, R.string.horoscope_date_pisces,0)
    )

    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Inicializamos el recyclerView
        recyclerView = findViewById(R.id.HoroscopeRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        //2. Creamos el adapter
        recyclerView.adapter  = HoroscopeAdapter(horoscopeList, this)


        //recyclerView.layoutManager = GridLayoutManager(this, 2) // Número de columna
    }

}