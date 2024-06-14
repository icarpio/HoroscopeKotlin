package com.example.horoscope

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var horoscopeList:List<Horoscope>

    private lateinit var recyclerView: RecyclerView

    lateinit var adapter:HoroscopeAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        horoscopeList = HoroscopeProvider.findAll()

        // 1. Inicializamos el recyclerView
        recyclerView = findViewById(R.id.HoroscopeRecyclerView)

        //2. Creamos el adapter
              //Adapter - private val onItemClickListener: (Int) -> Unit) :
        adapter = HoroscopeAdapter(horoscopeList) { position ->
            navigateToDetail(horoscopeList[position])
        }
        recyclerView.adapter  = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        //recyclerView.layoutManager = GridLayoutManager(this, 2) // Número de columna

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        val inflater:MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_activity_main, menu)

        val searchViewItem = menu.findItem(R.id.action_search)
        val searchView = searchViewItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText != null) {
                    horoscopeList = HoroscopeProvider.findAll().filter { getString(it.name).contains(newText,true) }
                    adapter.updateData(horoscopeList)
                }
                return true
            }
        })
        return true
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
                Log.i("MENU", "He hecho click en el menu de busqueda")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun navigateToDetail(horoscope: Horoscope) {
        val intent: Intent = Intent(this, SecondActivity::class.java)
        intent.putExtra(SecondActivity.EXTRA_HOROSCOPE_ID, horoscope.id)
        startActivity(intent)
    }



}