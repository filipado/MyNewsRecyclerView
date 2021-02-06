package com.example.mynewsrecyclerview.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynewsrecyclerview.api.retrofit.ApiClient
import com.example.mynewsrecyclerview.databinding.FragmentTopStoriesBinding
import com.example.mynewsrecyclerview.ui.adapters.TopStoriesAdapter
import retrofit2.HttpException
import java.io.IOException


class TopStoriesFragment : Fragment() {

    private var _binding: FragmentTopStoriesBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var topStoriesAdapter: TopStoriesAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTopStoriesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        lifecycleScope.launchWhenCreated {

            binding.pbTopStories.isVisible = true

            val response = try {
                ApiClient.retrofit.getTopStories()
            } catch (e: IOException) {
                Log.e(TAG, "IOException, you might not have internet connection")
                binding.pbTopStories.isVisible = false
                return@launchWhenCreated
            } catch (e: HttpException) {
                e.printStackTrace()
                Log.e(TAG, "HttpException, unexpected response")
                binding.pbTopStories.isVisible = false
                return@launchWhenCreated
            }


            if (response.isSuccessful && response.body() != null) {
                topStoriesAdapter.articles = response.body()!!.results
            } else {
                Log.e(TAG, "Response not successful")
            }
            binding.pbTopStories.isVisible = false
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "TopStoriesFragment"
    }

    private fun setupRecyclerView() = binding.rvTopStories.apply {
        topStoriesAdapter = TopStoriesAdapter()
        adapter = topStoriesAdapter
        layoutManager = LinearLayoutManager(this@TopStoriesFragment.context)
    }

}

