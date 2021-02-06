package com.example.mynewsrecyclerview.api.response

data class MovieReviewsResponse(
    val results: List<Result>
)

data class Result(
    val byline: String,
    val critics_pick: Int,
    val date_updated: String,
    val display_title: String,
    val headline: String,
    val link: Link,
    val mpaa_rating: String,
    val multimedia: Multimedia,
    val opening_date: String,
    val publication_date: String,
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
