package com.example.horoscope.utils

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context:Context) {
    companion object {
        const val FAVORITE_HOROSCOPE = "FAVORITE_HOROSCOPE"
        const val SESSION_KEY = "user session"
    }

    private val sharedPreferences:SharedPreferences

    init {
        sharedPreferences = context.getSharedPreferences(SESSION_KEY, Context.MODE_PRIVATE)
    }

    fun setFavoriteHoroscope(id: String) {
        val editor = sharedPreferences.edit()
        editor.putString(FAVORITE_HOROSCOPE, id)
        editor.apply()
    }

    fun getFavoriteHoroscope() : String? {
        return sharedPreferences.getString(FAVORITE_HOROSCOPE, null)
    }

    fun isFavorite(horoscopeId: String) : Boolean {
        return getFavoriteHoroscope()?.equals(horoscopeId) ?: false
    }
}