package com.example.mynewsrecyclerview

import com.example.mynewsrecyclerview.models.Article
import retrofit2.Response
import retrofit2.http.GET

interface NytAPI {

    @GET("/home.json?api-key=akNENukJUoxU02CPyrlUCBgRB8225M2r")
    suspend fun getArticles(): Response<List<Article>>

}