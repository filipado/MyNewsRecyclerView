package com.example.mynewsrecyclerview.api.response

import com.google.gson.annotations.SerializedName

data class MostViewedResponse(
        val results: ArrayList<MostPopularArticle>
)

data class MostPopularArticle(
        val abstract: String,
        val published_date: String,
        val section: String,
        val title: String,
        val url: String,
        val media: ArrayList<MostPopularMedia>
)

data class MostPopularMedia(@SerializedName("media-metadata") val mostPopularMetaData: ArrayList<MostPopularMetaData>)

data class MostPopularMetaData(val url: String)
