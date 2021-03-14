package com.example.mynewsrecyclerview.api.response

import org.junit.Assert
import org.junit.Test



class MovieReviewsResponseTest {

    @Test
    fun `validate MovieReviewsResponse`() {
        val movieResults = ArrayList<Result>()

        val response = MovieReviewsResponse(movieResults)

        Assert.assertEquals(response.results, movieResults)
    }

    @Test
    fun `validate Result`() {
        val movieResult = Result(
            "byline",
            Link("Some text",
                "type of the link",
                "https://www.newyorktimes.com/movie_review_link"),
            Multimedia("", "https://newyorktimes.com/that_movie_picture"),
            "2022-01-03",
            "This movie is very important"
        )

        Assert.assertEquals(movieResult.multimedia.src,
            "https://newyorktimes.com/that_movie_picture")

    }

}