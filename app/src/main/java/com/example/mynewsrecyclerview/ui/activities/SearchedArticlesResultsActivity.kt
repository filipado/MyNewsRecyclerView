package com.example.mynewsrecyclerview.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mynewsrecyclerview.databinding.ActivitySearchedArticlesBinding

class SearchedArticlesResultsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchedArticlesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchedArticlesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // kod na recyclerView

        // kod na request z API
    }

    // Recycler View Adapter
}