package com.example.mynewsrecyclerview.api.retrofit

import com.example.mynewsrecyclerview.BuildConfig
import com.example.mynewsrecyclerview.util.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    val retrofit: ArticlesApi by lazy {

        val interceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            interceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        val httpClient = OkHttpClient.Builder().addNetworkInterceptor(interceptor).build()

        Retrofit.Builder()
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ArticlesApi::class.java)

    }
}