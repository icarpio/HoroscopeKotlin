package com.example.horoscope.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.horoscope.data.Horoscope
import com.example.horoscope.data.HoroscopeProvider
import com.example.horoscope.R
import com.example.horoscope.api.RetrofitInstance
import com.example.horoscope.utils.SessionManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SecondActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_HOROSCOPE_ID = "HOROSCOPE_ID"
    }

    lateinit var horoscope: Horoscope
    lateinit var favoriteMenuItem:MenuItem
    lateinit var session:SessionManager
    lateinit var horoscopes: List<Horoscope>



    var isFavorite = false
    var currentPosition = 0

    lateinit var nameSecondTextView: TextView
    lateinit var descSecondTextView: TextView
    lateinit var lsImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        session = SessionManager(this)

        val id: String = intent.getStringExtra(EXTRA_HOROSCOPE_ID)!!
        horoscope  = HoroscopeProvider.findById(id)!!
        currentPosition = HoroscopeProvider.getIndex(horoscope)

        isFavorite = session.getFavoriteHoroscope()?.equals(horoscope.id) ?: false

        nameSecondTextView = findViewById(R.id.nameSecondTextView)
        descSecondTextView = findViewById(R.id.DescSecondTextView)
        lsImageView = findViewById(R.id.lsImageView)

        loadData()


        //Boton Atras
        /*val backButton: Button = findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }*/

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Obtiene la lista de todos los horóscopos
        horoscopes = HoroscopeProvider.findAll()

        // Botón para cargar el siguiente horóscopo
        val btnSiguiente: Button = findViewById(R.id.nextButton)
        btnSiguiente.setOnClickListener {
            //Suma uno a la posicion actual
            currentPosition++

            //Si llega a la ultima posicion, vuelve a la posicion 0
            if (currentPosition >= horoscopes.size) {
                currentPosition = 0
            }

            horoscope = horoscopes[currentPosition]
            loadData()
        }

    }
    fun loadData() {
        nameSecondTextView.setText(horoscope.name)
        descSecondTextView.setText(horoscope.description)
        lsImageView.setImageResource(horoscope.logo)
        supportActionBar?.setTitle(horoscope.name)
        supportActionBar?.setSubtitle(horoscope.description)
        show(horoscope.id)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_activity_second, menu)
        favoriteMenuItem = menu.findItem(R.id.action_favourite)
        setFavoriteIcon()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.action_favourite -> {
                if (isFavorite) {
                    session.setFavoriteHoroscope("")
                } else {
                    session.setFavoriteHoroscope(horoscope.id)
                }
                isFavorite = !isFavorite
                setFavoriteIcon()
                true
            }
            R.id.action_share -> {
                val sendIntent = Intent()
                sendIntent.setAction(Intent.ACTION_SEND)
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
                sendIntent.setType("text/plain")

                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }


    private fun showError(errorApi: String) {
        Toast.makeText(this, errorApi, Toast.LENGTH_SHORT).show()
    }

    //Llamada a la API
    private fun show(id: String) {
        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        progressBar.visibility = View.VISIBLE

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitInstance.api.getDailyHoroscope(sign = id)
                withContext(Dispatchers.Main) {
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
                    progressBar.visibility = View.GONE
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    showError("Exception: ${e.message}")
                    progressBar.visibility = View.GONE
                }
            }
        }
    }

    fun setFavoriteIcon(){
        if(isFavorite){
            favoriteMenuItem.setIcon(R.drawable.favorite)
        }else{
            favoriteMenuItem.setIcon(R.drawable.favorite_border)
        }
    }


}









