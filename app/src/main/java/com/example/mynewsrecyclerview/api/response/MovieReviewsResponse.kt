package com.example.mynewsrecyclerview.api.response

data class MovieReviewsResponse(
    val results: ArrayList<Result>
)

data class Result(
    val byline: String,
    val link: Link,
    val multimedia: Multimedia,
    val opening_date: String,
    val summary_short: String
)

data class Link(
    val suggested_link_text: String,
    val type: String,
    val url: String
)

//

/*
// This data class applies to thumbnails URLs of both TopStoriesArticles and MovieReviewsResponse
data class Multimedia (
    // Top Stories Articles:
    val url: String,

    // Movie Reviews Response:
    val src: String
)
 */
