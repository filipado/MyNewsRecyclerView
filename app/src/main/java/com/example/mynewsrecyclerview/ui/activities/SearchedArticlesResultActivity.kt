package com.example.mynewsrecyclerview.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynewsrecyclerview.api.retrofit.ApiClient
import com.example.mynewsrecyclerview.databinding.ActivitySearchedArticlesBinding
import com.example.mynewsrecyclerview.ui.adapters.SearchResultsAdapter
import com.example.mynewsrecyclerview.ui.fragments.TopStoriesFragment
import retrofit2.HttpException
import java.io.IOException

class SearchedArticlesResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchedArticlesBinding

    private lateinit var searchResultsAdapter: SearchResultsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchedArticlesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Importing values from Search Activity

        val searchQuery = intent.getStringExtra("EXTRA_QUERY")
        val startDate = intent.getStringExtra("EXTRA_DATE_START")
        val endDate = intent.getStringExtra("EXTRA_DATE_END")

        val arts = intent.getStringExtra("EXTRA_ARTS")
        val business = intent.getStringExtra("BUSINESS")
        val entrepreneurs = intent.getStringExtra("EXTRA_ENTREPRENEURS")
        val politics = intent.getStringExtra("EXTRA_POLITICS")
        val sports = intent.getStringExtra("EXTRA_SPORTS")
        val travel = intent.getStringExtra("EXTRA_TRAVEL")


        setupRecyclerView()

        lifecycleScope.launchWhenCreated {

            binding.pbSearchResults.isVisible = true

            val response = try {
                ApiClient.retrofit.getSearchedArticles(searchQuery,
                    startDate,
                    endDate, "$arts $business $entrepreneurs" +
                        "$politics $sports $travel")

            } catch (e: IOException) {
                Log.e(TopStoriesFragment.TAG, "IOException, you might not have internet connection")
                binding.pbSearchResults.isVisible = false
                return@launchWhenCreated
            } catch (e: HttpException) {
                e.printStackTrace()
                Log.e(TopStoriesFragment.TAG, "HttpException, unexpected response")
                binding.pbSearchResults.isVisible = false
                return@launchWhenCreated
            }


            if (response.isSuccessful && response.body() != null) {
                searchResultsAdapter.searchResults = response.body()!!.response.docs
            } else {
                Log.e(TopStoriesFragment.TAG, "Response not successful")
            }
            binding.pbSearchResults.isVisible = false
        }
    }

    companion object {
        const val TAG = "SearchedArticlesResultsActivity"
    }

    private fun setupRecyclerView() = binding.searchedArticlesRecyclerView.apply {
        searchResultsAdapter = SearchResultsAdapter()
        adapter = searchResultsAdapter
        layoutManager = LinearLayoutManager(this@SearchedArticlesResultActivity)
    }

}