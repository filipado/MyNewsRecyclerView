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
import com.example.mynewsrecyclerview.databinding.FragmentMovieReviewsBinding
import com.example.mynewsrecyclerview.ui.adapters.MovieReviewsAdapter
import retrofit2.HttpException
import java.io.IOException


class MovieReviewsFragment : Fragment() {

    private var _binding: FragmentMovieReviewsBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var movieReviewsAdapter: MovieReviewsAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieReviewsBinding.inflate(layoutInflater, container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        lifecycleScope.launchWhenCreated {

            binding.pbMovieReviews.isVisible = true

            val response = try {
                ApiClient.retrofit.getMovieReviews()
            } catch (e: IOException) {
                Log.e(TAG, "IOException, you might not have internet connection")
                binding.pbMovieReviews.isVisible = false
                return@launchWhenCreated
            } catch (e: HttpException) {
                e.printStackTrace()
                Log.e(TAG, "HttpException, unexpected response")
                binding.pbMovieReviews.isVisible = false
                return@launchWhenCreated
            }


            if (response.isSuccessful && response.body() != null) {
                movieReviewsAdapter.movieReviews = response.body()!!.results
            } else {
                Log.e(TAG, "Response not successful")
            }
            binding.pbMovieReviews.isVisible = false
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "MovieReviewsFragment"
    }

    private fun setupRecyclerView() = binding.rvMovieReviews.apply {
        movieReviewsAdapter = MovieReviewsAdapter()
        adapter = movieReviewsAdapter
        layoutManager = LinearLayoutManager(this@MovieReviewsFragment.context)
    }

}