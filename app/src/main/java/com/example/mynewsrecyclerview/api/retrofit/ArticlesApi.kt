package com.example.mynewsrecyclerview.api.retrofit

import com.example.mynewsrecyclerview.api.response.MostViewedResponse
import com.example.mynewsrecyclerview.api.response.MovieReviewsResponse
import com.example.mynewsrecyclerview.api.response.SearchResponse
import com.example.mynewsrecyclerview.api.response.TopStoryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

const val apiKey: String = "akNENukJUoxU02CPyrlUCBgRB8225M2r"

interface ArticlesApi {


    //getting TOP STORIES response from API
    @GET("topstories/v2/home.json?api-key=$apiKey")
    suspend fun getTopStories(): Response<TopStoryResponse>

    //getting MOST POPULAR response from API
    @GET("mostpopular/v2/viewed/1.json?api-key=$apiKey")
    suspend fun getMostPopular(): Response<MostViewedResponse>

    //getting MOVIE REVIEWS response from API
    @GET("movies/v2/reviews/all.json?api-key=$apiKey")
    suspend fun getMovieReviews(): Response<MovieReviewsResponse>

    //getting SEARCHED ARTICLES response from API
    @GET("")
    suspend fun getSearchedArticles(
            @Query("q") query: String?,
            @Query("begin_date") beginDate: String?,
            @Query("end_date") endDate: String?,
            @Query("fq") sections: String?
    ): Response<SearchResponse>

}