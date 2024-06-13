package com.example.horoscope

class HoroscopeProvider {
        companion object {
            private val horoscopeList: List<Horoscope> = listOf(
                Horoscope("aries", R.string.horoscope_name_aries, R.string.horoscope_date_aries,R.drawable.aries),
                Horoscope("taurus", R.string.horoscope_name_taurus, R.string.horoscope_date_taurus, R.drawable.taurus),
                Horoscope("gemini", R.string.horoscope_name_gemini, R.string.horoscope_date_gemini,R.drawable.gemini),
                Horoscope("cancer", R.string.horoscope_name_cancer, R.string.horoscope_date_cancer, R.drawable.cancer),
                Horoscope("leo", R.string.horoscope_name_leo, R.string.horoscope_date_leo,R.drawable.leo),
                Horoscope("virgo", R.string.horoscope_name_virgo, R.string.horoscope_date_virgo,R.drawable.virgo),
                Horoscope("libra", R.string.horoscope_name_libra, R.string.horoscope_date_libra, R.drawable.libra),
                Horoscope("scorpio", R.string.horoscope_name_scorpio, R.string.horoscope_date_scorpio, R.drawable.scorpio),
                Horoscope("sagittarius", R.string.horoscope_name_sagittarius, R.string.horoscope_date_sagittarius,R.drawable.sagittarius),
                Horoscope("aquarius", R.string.horoscope_name_aquarius, R.string.horoscope_date_aquarius, R.drawable.aquarius),
                Horoscope("pisces", R.string.horoscope_name_pisces, R.string.horoscope_date_pisces,R.drawable.pisces)
            )

            fun findAll() : List<Horoscope> {
                return horoscopeList
            }

            fun findById(id: String) : Horoscope? {
                return horoscopeList.find { it.id == id }
            }
        }
}