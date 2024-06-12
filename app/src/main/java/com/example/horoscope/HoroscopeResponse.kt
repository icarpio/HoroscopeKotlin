package com.example.horoscope

import com.google.gson.annotations.SerializedName

data class HoroscopeResponse(
    val data: HoroscopeData,
    val status: Int,
    val success: Boolean
)

data class HoroscopeData(
    @SerializedName("date") val date: String,
    @SerializedName("horoscope_data")val horoscope_data: String
)

