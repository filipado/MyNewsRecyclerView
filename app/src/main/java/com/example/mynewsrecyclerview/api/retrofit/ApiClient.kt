package com.example.mynewsrecyclerview.api.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private var BASE_URL = "https://api.nytimes.com/svc/"

    val retrofit: ArticlesApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ArticlesApi::class.java)
    }
}