package com.example.horoscope

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    val horoscopeList: List<Horoscope> = listOf(
        Horoscope("aries", R.string.horoscope_name_aries, R.string.horoscope_date_aries, R.drawable.aries),
        Horoscope("aries", R.string.horoscope_name_taurus, R.string.horoscope_date_taurus, R.drawable.taurus),
        Horoscope("aries", R.string.horoscope_name_gemini, R.string.horoscope_date_gemini, R.drawable.gemini),
        Horoscope("aries", R.string.horoscope_name_cancer, R.string.horoscope_date_cancer, R.drawable.cancer),
        Horoscope("aries", R.string.horoscope_name_leo, R.string.horoscope_date_leo, R.drawable.leo),
        Horoscope("aries", R.string.horoscope_name_virgo, R.string.horoscope_date_virgo, R.drawable.virgo),
        Horoscope("aries", R.string.horoscope_name_libra, R.string.horoscope_date_libra, R.drawable.libra),
        Horoscope("aries", R.string.horoscope_name_scorpio, R.string.horoscope_date_scorpio, R.drawable.scorpio),
        Horoscope("aries", R.string.horoscope_name_sagittarius, R.string.horoscope_date_sagittarius, R.drawable.sagittarius),
        Horoscope("aries", R.string.horoscope_name_capricorn, R.string.horoscope_date_capricorn, R.drawable.capricorn),
        Horoscope("aries", R.string.horoscope_name_aquarius, R.string.horoscope_date_aquarius, R.drawable.aquarius),
        Horoscope("aries", R.string.horoscope_name_pisces, R.string.horoscope_date_pisces, R.drawable.pisces)
    )

    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Inicializamos el recyclerView
        recyclerView = findViewById(R.id.HoroscopeRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        //2. Creamos el adapter
        val adapter = HoroscopeAdapter(horoscopeList)
        recyclerView.adapter = adapter

        //recyclerView.layoutManager = GridLayoutManager(this, 2) // Número de columna
    }
}