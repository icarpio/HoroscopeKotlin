package com.example.horoscope

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


// 3. Crear Adapter para que le diga al recyvler view que datos mostrar
class HoroscopeAdapter (private val dataSet: List<Horoscope>):
    RecyclerView.Adapter<HoroscopeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoroscopeViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_horoscope, parent, false)

        return HoroscopeViewHolder(view)
    }

    // Create new views (invoked by the layout manager)


    //override fun getItemCount(): Int = dataSet.size
    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: HoroscopeViewHolder, position: Int) {
        val item = dataSet[position]
        holder.render(item)
        // val context = holder.logoImageView.context
        //holder.logoImageView.setImageDrawable(context.getDrawable(item.logo))

    }

}





