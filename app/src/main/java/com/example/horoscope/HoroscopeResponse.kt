package com.example.horoscope

import com.google.gson.annotations.SerializedName

data class HoroscopeResponse(
    val data: HoroscopeData,
    val status: Int,
    val success: Boolean
)

data class HoroscopeData(
    val date: String,
    val horoscope_data: String
)

