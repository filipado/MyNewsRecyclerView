package com.example.mynewsrecyclerview.api.response

import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test

class TopStoryResponseTest {


    @Test
    fun `validate TopStoryResponse`() {
        val topStoryList = ArrayList<TopStory>()

        val storyResponse = TopStoryResponse(topStoryList)

        assertEquals(storyResponse.results, topStoryList)
    }

    @Test
    fun `validate TopStory`() {
        val multimediaList = ArrayList<Multimedia>()


        val topStory = TopStory(
            "2021-03-01",
            "thriller",
            "Best Story of the year is of coming age tale",
            multimediaList,
            "https://www.newyorktimes.com/top_story_of_the_decade"
            )

        assertEquals(topStory.published_date, "2021-03-01")
    }

    @Test
    fun `validate Multimedia`() {

        val multimediaTopStory = Multimedia (
            "url",
            "src"
                )

        assertEquals(multimediaTopStory.src, "src")
    }
}