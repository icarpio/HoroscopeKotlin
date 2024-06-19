package com.example.horoscope.adapters

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscope.R
import com.example.horoscope.data.Horoscope
import com.example.horoscope.utils.SessionManager


class HoroscopeViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    val nameTextView: TextView
    val descriptionTextView: TextView
    val logoImageView: ImageView
    val favoriteImageView: ImageView


    init {
        // Define click listener for the ViewHolder's View
        nameTextView = view.findViewById(R.id.nameTextView)
        descriptionTextView =  view.findViewById(R.id.descriptionTextView)
        logoImageView = view.findViewById(R.id.lsImageView)
        // Añadimos logs para verificar la inicialización
        favoriteImageView = view.findViewById(R.id.favoriteImageView)
    }

    fun render(item: Horoscope){
        nameTextView.setText(item.name)
        descriptionTextView.setText(item.description)
        logoImageView.setImageResource(item.logo)

        val context = itemView.context
        var isFavorite = SessionManager(context).isFavorite(item.id)
        if (isFavorite) {
            favoriteImageView.visibility = View.VISIBLE
        } else {
            favoriteImageView.visibility = View.GONE
        }

    }

}