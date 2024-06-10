package com.example.horoscope

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    val horoscopeList:List<Horoscope> = listOf(
        Horoscope("aries","Aries", "",0),
        Horoscope("tauro","Taurus", "",0),
        Horoscope("geminis","Gemini", "",0),
        Horoscope("cancer","Cancer", "",0),
        Horoscope("leo","Leo", "",0),
        Horoscope("virgo","Virgo", "",0),
        Horoscope("libra","Libra", "",0),
        Horoscope("escorpio","Scorpio", "",0),
        Horoscope("sagitario","Sagitarius", "",0),
        Horoscope("capricornio","Capricorn", "",0),
        Horoscope("acuario","Aquarius", "",0),
        Horoscope("piscis","Pisces", "",0)
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}