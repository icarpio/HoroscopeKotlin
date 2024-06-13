package com.example.horoscope

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url


interface HoroscopeApiService {
    @GET("api/v1/get-horoscope/daily")
    suspend fun getDailyHoroscope(@Query("sign") sign: String, @Query("day") day: String = "TODAY"): Response<HoroscopeResponse>
}