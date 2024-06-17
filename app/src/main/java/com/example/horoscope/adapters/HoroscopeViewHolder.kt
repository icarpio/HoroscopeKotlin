package com.example.horoscope.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.horoscope.R
import com.example.horoscope.data.Horoscope


class HoroscopeViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    val nameTextView: TextView
    val descriptionTextView: TextView
    val logoImageView: ImageView

    init {
        // Define click listener for the ViewHolder's View
        nameTextView = view.findViewById(R.id.nameTextView)
        descriptionTextView =  view.findViewById(R.id.descriptionTextView)
        logoImageView = view.findViewById(R.id.logoImageView)
    }

    fun render(item: Horoscope){
        nameTextView.setText(item.name)
        descriptionTextView.setText(item.description)
        logoImageView.setImageResource(item.logo)
    }

}