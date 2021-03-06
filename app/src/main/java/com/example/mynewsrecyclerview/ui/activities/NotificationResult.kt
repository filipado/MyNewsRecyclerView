package com.example.mynewsrecyclerview.ui.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynewsrecyclerview.api.retrofit.ApiClient
import com.example.mynewsrecyclerview.databinding.ActivityNotificationResultBinding
import com.example.mynewsrecyclerview.ui.adapters.NotificationsAdapter
import com.example.mynewsrecyclerview.ui.fragments.TopStoriesFragment
import com.jakewharton.threetenabp.AndroidThreeTen
import org.threeten.bp.LocalDate
import retrofit2.HttpException
import java.io.IOException
import java.util.*

class NotificationResult : AppCompatActivity() {

    private lateinit var binding: ActivityNotificationResultBinding

    private lateinit var notificationsAdapter: NotificationsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // INITIATE THREE_TEN LIBRARY:

        AndroidThreeTen.init(this)

        // INITIATE SHARED PREFERENCES AND GET ALL THE NECESSARY VALUES

        val sharedPref = getSharedPreferences("NotificationsActivity", Context.MODE_PRIVATE)
        val savedSearchQuery = sharedPref.getString("searchQuery", null)
        val savedArtsCheckBox = sharedPref.getString("arts", null)
        val savedBusinessCheckBox = sharedPref.getString("business", null)
        val savedEntrepreneursCheckBox = sharedPref.getString("entrepreneurs", null)
        val savedPoliticsCheckBox = sharedPref.getString("politics", null)
        val savedSportsCheckBox = sharedPref.getString("sports", null)
        val savedTravelCheckBox = sharedPref.getString("travel", null)

        // SETTING UP DATE SCOPE FOR NOTIFICATIONS -> BETWEEN YESTERDAY AND TODAY INCLUSIVE OF TODAY

        val yesterday = LocalDate.now().minusDays(1).toString()
        val today = LocalDate.now().toString()

        val myFormat = org.threeten.bp.format.DateTimeFormatter.ofPattern("yyyyMMdd")

        val convertedStartDate = yesterday.format(myFormat)
        val convertedEndDate = today.format(myFormat)

        // SET UP RECYCLER VIEW

        setupRecyclerView()

        // GETTING NOTIFICATIONS ARTICLES USING RETROFIT INSTANCE

        lifecycleScope.launchWhenCreated {

            binding.progressBarNotifications.isVisible = true

            val response = try {
                ApiClient.retrofit.getSearchedArticles(
                    savedSearchQuery,
                    convertedStartDate,
                    convertedEndDate,
                    "$savedArtsCheckBox $savedBusinessCheckBox $savedEntrepreneursCheckBox" +
                            "$savedPoliticsCheckBox $savedSportsCheckBox $savedTravelCheckBox"
                )
            } catch (e: IOException) {
                Log.e(TopStoriesFragment.TAG, "IOException, you might not have internet connection")
                binding.progressBarNotifications.isVisible = false
                return@launchWhenCreated
            } catch (e: HttpException) {
                e.printStackTrace()
                Log.e(TopStoriesFragment.TAG, "HttpException, unexpected response")
                binding.progressBarNotifications.isVisible = false
                return@launchWhenCreated
            }

            if (response.isSuccessful && response.body() != null) {
                notificationsAdapter.searchResults = response.body()!!.response.docs
            } else {
                Log.e(TopStoriesFragment.TAG, "Response not successful")
            }
            binding.progressBarNotifications.isVisible = false


        }


    }

    private fun setupRecyclerView() = binding.NotificationsRecyclerView.apply {
        notificationsAdapter = NotificationsAdapter()
        adapter = notificationsAdapter
        layoutManager = LinearLayoutManager(this@NotificationResult)
    }


}