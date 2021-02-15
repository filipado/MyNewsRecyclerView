package com.example.mynewsrecyclerview.api.response

data class TopStoryResponse(val results : List<TopStory>)


data class TopStory(
    val published_date: String,
    val section: String,
    val title: String,
    val multimedia: List<Multimedia>,
    val url: String
)

// This data class applies to thumbnails URLs of both TopStoriesArticles and MovieReviewsResponse
data class Multimedia (
    // Top Stories Articles:
    val url: String,
    // Movie Reviews Response:
    val src: String
)