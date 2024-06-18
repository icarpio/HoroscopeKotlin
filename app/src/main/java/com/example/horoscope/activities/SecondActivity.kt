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


    var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        session = SessionManager(this)

        val id: String = intent.getStringExtra(EXTRA_HOROSCOPE_ID)!!
        horoscope  = HoroscopeProvider.findById(id)!!

        isFavorite = session.getFavoriteHoroscope()?.equals(horoscope.id) ?: false

        findViewById<TextView>(R.id.nameSecondTextView).setText(horoscope.name)
        findViewById<TextView>(R.id.DescSecondTextView).setText(horoscope.description)
        findViewById<ImageView>(R.id.lsImageView).setImageResource(horoscope.logo)


        //Boton Atras
        /*val backButton: Button = findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }*/

        supportActionBar?.setTitle(horoscope.name)
        supportActionBar?.setSubtitle(horoscope.description)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

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









