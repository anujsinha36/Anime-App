package com.example.jikan.api

import com.example.jikan.utils.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {
    companion object {
        private var retrofitInstance: AnimeAPI? = null

        fun getRetrofitInstance(): AnimeAPI {
           if (retrofitInstance == null) {
                retrofitInstance = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(AnimeAPI::class.java)
            }
            return retrofitInstance!!
        }
    }
}