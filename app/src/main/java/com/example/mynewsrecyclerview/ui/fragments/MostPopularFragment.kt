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
import com.example.mynewsrecyclerview.databinding.FragmentMostPopularBinding
import com.example.mynewsrecyclerview.ui.adapters.MostPopularAdapter
import retrofit2.HttpException
import java.io.IOException


class MostPopularFragment : Fragment() {

    private var _binding: FragmentMostPopularBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var mostPopularAdapter: MostPopularAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMostPopularBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        lifecycleScope.launchWhenCreated {

            binding.pbMostPopular.isVisible = true

            val response = try {
                ApiClient.retrofit.getMostPopular()
            } catch (e: IOException) {
                Log.e(TAG, "IOException, you might not have internet connection")
                binding.pbMostPopular.isVisible = false
                return@launchWhenCreated
            } catch (e: HttpException) {
                e.printStackTrace()
                Log.e(TAG, "HttpException, unexpected response")
                binding.pbMostPopular.isVisible = false
                return@launchWhenCreated
            }


            if (response.isSuccessful && response.body() != null) {
                mostPopularAdapter.mostPopularArticles = response.body()!!.results
            } else {
                Log.e(TAG, "Response not successful")
            }
            binding.pbMostPopular.isVisible = false
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "MostPopularFragment"
    }

    private fun setupRecyclerView() = binding.rvMostPopular.apply {
        mostPopularAdapter = MostPopularAdapter()
        adapter = mostPopularAdapter
        layoutManager = LinearLayoutManager(this@MostPopularFragment.context)
    }

}