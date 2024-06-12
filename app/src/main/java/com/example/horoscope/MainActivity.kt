package com.example.horoscope

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val horoscopeList: List<Horoscope> = listOf(
        Horoscope("aries", R.string.horoscope_name_aries, R.string.horoscope_date_aries,R.drawable.aries),
        Horoscope("taurus", R.string.horoscope_name_taurus, R.string.horoscope_date_taurus, R.drawable.taurus),
        Horoscope("gemini", R.string.horoscope_name_gemini, R.string.horoscope_date_gemini,R.drawable.gemini),
        Horoscope("cancer", R.string.horoscope_name_cancer, R.string.horoscope_date_cancer, R.drawable.cancer),
        Horoscope("leo", R.string.horoscope_name_leo, R.string.horoscope_date_leo,R.drawable.leo),
        Horoscope("virgo", R.string.horoscope_name_virgo, R.string.horoscope_date_virgo,R.drawable.virgo),
        Horoscope("libra", R.string.horoscope_name_libra, R.string.horoscope_date_libra, R.drawable.libra),
        Horoscope("scorpio", R.string.horoscope_name_scorpio, R.string.horoscope_date_scorpio, R.drawable.scorpio),
        Horoscope("sagittarius", R.string.horoscope_name_sagittarius, R.string.horoscope_date_sagittarius,R.drawable.sagittarius),
        Horoscope("aquarius", R.string.horoscope_name_aquarius, R.string.horoscope_date_aquarius, R.drawable.aquarius),
        Horoscope("pisces", R.string.horoscope_name_pisces, R.string.horoscope_date_pisces,R.drawable.pisces)
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