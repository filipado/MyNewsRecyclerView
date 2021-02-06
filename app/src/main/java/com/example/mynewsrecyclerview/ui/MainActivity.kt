package com.example.mynewsrecyclerview.ui

import android.content.ClipData
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.View.GONE
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.mynewsrecyclerview.R
import com.example.mynewsrecyclerview.databinding.ActivityMainBinding
import com.example.mynewsrecyclerview.ui.fragments.MostPopularFragment
import com.example.mynewsrecyclerview.ui.fragments.MovieReviewsFragment
import com.example.mynewsrecyclerview.ui.fragments.SearchedArticlesFragment
import com.example.mynewsrecyclerview.ui.fragments.TopStoriesFragment
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragments: ArrayList<Fragment> = arrayListOf(TopStoriesFragment(), MostPopularFragment(), MovieReviewsFragment())
        val viewPager: ViewPager2 = binding.viewPager
        val adapter = ViewPagerAdapter(fragments, this)
        val searchButton = findViewById<View>(R.id.search)


        binding.topAppBar.setNavigationOnClickListener {
            // Handle navigation icon press
        }

        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.search -> {
                    // Handle search icon press

                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, SearchedArticlesFragment())
                        .setReorderingAllowed(true)
                        .addToBackStack("name") // name can be null
                        .commit()
                    true
                }
                R.id.more -> {
                    // Handle more item (inside overflow menu) press
                    true
                }
                else -> false
            }
        }

        viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = ""
                    tab.setIcon(R.drawable.ic_top_stories)
                }
                1 -> {
                    tab.text = ""
                    tab.setIcon(R.drawable.ic_baseline_star_24)
                }
                2 -> {
                    tab.text = ""
                    tab.setIcon(R.drawable.ic_film_reviews)
                }
            }
        }.attach()
    }
}

