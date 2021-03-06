package com.example.mynewsrecyclerview.api.response

import org.junit.Test
import org.junit.Assert

val results = ArrayList<MostPopularArticle>()
val media = ArrayList<MostPopularMedia>()
val metadata = ArrayList<MostPopularMetaData>()
val link = "https://www.newyorktimes.com/media_url"

class MostViewedResponseTest {

    @Test
    fun `validate Most Viewed Response`() {

        val response = MostViewedResponse (results)

        Assert.assertEquals(response.results, results)

    }

    @Test
    fun `validate MostPopularArticle`() {

        val article = MostPopularArticle (
            "politics",
            "2021-03-08",
            "opinion",
            "Very important article",
            "https://www.onet.pl/",
            media)

        Assert.assertEquals(article.abstract, "politics")

    }

    @Test
    fun `validate MostPopularMedia`() {

        val media = MostPopularMedia (metadata)

        Assert.assertEquals(media.mostPopularMetaData, metadata)
    }

    @Test
    fun `validate MostPopularMetaData`() {

        val metaData = MostPopularMetaData (link)

        Assert.assertEquals(metaData.url, link)
    }

}