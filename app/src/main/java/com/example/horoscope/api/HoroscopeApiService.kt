package com.example.horoscope.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface HoroscopeApiService {
    @GET("api/v1/get-horoscope/daily")
    suspend fun getDailyHoroscope(@Query("sign") sign: String, @Query("day") day: String = "TODAY"): Response<HoroscopeResponse>
}