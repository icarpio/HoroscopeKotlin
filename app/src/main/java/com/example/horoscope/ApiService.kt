package com.example.horoscope

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface HoroscopeApiService {
    @GET("get-horoscope/daily")
    fun getDailyHoroscope(
        @Query("sign") sign: String,
        @Query("day") day: String = "TODAY"
    ): Call<HoroscopeResponse>
}