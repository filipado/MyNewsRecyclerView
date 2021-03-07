package com.example.mynewsrecyclerview.ui

import android.content.ClipData
import android.content.Intent
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
import com.example.mynewsrecyclerview.ui.activities.NotificationsActivity
import com.example.mynewsrecyclerview.ui.activities.SearchActivity
import com.example.mynewsrecyclerview.ui.fragments.*
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // space added, just testing GitHub Actions

        supportActionBar?.hide()

        val fragments: ArrayList<Fragment> = arrayListOf(TopStoriesFragment(), MostPopularFragment(), MovieReviewsFragment())
        val viewPager: ViewPager2 = binding.viewPager
        val adapter = ViewPagerAdapter(fragments, this)

        binding.topAppBar.setNavigationOnClickListener {
            // Handle navigation icon press
        }


        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.search -> {
                    // Handle search icon press
                    val searchActivityIntent = Intent(baseContext, SearchActivity::class.java)
                    startActivity(searchActivityIntent)
                    true
                }
                R.id.more -> {
                    // Handle more item (inside overflow menu) press
                    val notificationsActivityIntent = Intent(baseContext, NotificationsActivity::class.java)
                    startActivity(notificationsActivityIntent)
                    true
                }
                else -> false
            }
        }



        viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Top stories"
                    // tab.setIcon(R.drawable.ic_top_stories)
                }
                1 -> {
                    tab.text = "Most popular"
                    // tab.setIcon(R.drawable.ic_baseline_star_24)
                }
                2 -> {
                    tab.text = "Movie reviews"
                    // tab.setIcon(R.drawable.ic_film_reviews)
                }
            }
        }.attach()
    }
}

