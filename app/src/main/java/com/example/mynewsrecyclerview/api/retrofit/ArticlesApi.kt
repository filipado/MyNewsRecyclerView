package com.example.mynewsrecyclerview.api.retrofit

import com.example.mynewsrecyclerview.api.response.*
import com.example.mynewsrecyclerview.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ArticlesApi {

    //getting TOP STORIES response from API
    @GET("topstories/v2/home.json")
    suspend fun getTopStories(
        @Query("api-key") apiKey : String = API_KEY
    ): Response<TopStoryResponse>

    //getting MOST POPULAR response from API
    @GET("mostpopular/v2/viewed/1.json")
    suspend fun getMostPopular(
        @Query("api-key") apiKey : String = API_KEY
    ): Response<MostViewedResponse>

    //getting MOVIE REVIEWS response from API
    @GET("movies/v2/reviews/all.json")
    suspend fun getMovieReviews(
        @Query("api-key") apiKey : String = API_KEY
    ): Response<MovieReviewsResponse>

    //getting SEARCHED ARTICLES response from API
    @GET("search/v2/articlesearch.json")
    suspend fun getSearchedArticles(
            @Query("q") query: String?,
            @Query("begin_date") beginDate: String?,
            @Query("end_date") endDate: String?,
            @Query("fq") sections: String?,
            @Query("api-key") apiKey : String = API_KEY
    ): Response<SearchResponse>

    //getting SEARCHED ARTICLES about Obama
    @GET("search/v2/articlesearch.json")
    suspend fun getObamaArticles(
        @Query("q") query: String? = "obama",
        @Query("begin_date") beginDate: String? = "20200202",
        @Query("end_date") endDate: String? = "20210602",
        @Query("fq") sections: String? = "",
        @Query("api-key") apiKey : String = API_KEY
    ): Response<SearchResponse>

}

//https://api.nytimes.com/svc/search/v2/articlesearch.json?q=obama&fq=news_desk:(Sports)&begin_date=20120101&end_date=20121231&api-key=akNENukJUoxU02CPyrlUCBgRB8225M2r