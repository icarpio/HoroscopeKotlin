package com.example.horoscope

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var horoscopeList:List<Horoscope>

    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        horoscopeList = HoroscopeProvider.findAll()

        // 1. Inicializamos el recyclerView
        recyclerView = findViewById(R.id.HoroscopeRecyclerView)

        //2. Creamos el adapter
              //Adapter - private val onItemClickListener: (Int) -> Unit) :
        val adapter = HoroscopeAdapter(horoscopeList) { position ->
            navigateToDetail(horoscopeList[position])
        }
        recyclerView.adapter  = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        //recyclerView.layoutManager = GridLayoutManager(this, 2) // Número de columna

    }


    fun navigateToDetail(horoscope: Horoscope) {
        val intent: Intent = Intent(this, SecondActivity::class.java)
        intent.putExtra(SecondActivity.EXTRA_HOROSCOPE_ID, horoscope.id)
        startActivity(intent)
    }



}