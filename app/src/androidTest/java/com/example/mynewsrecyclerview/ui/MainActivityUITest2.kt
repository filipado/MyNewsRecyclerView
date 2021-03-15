package com.example.mynewsrecyclerview.ui

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.mynewsrecyclerview.R
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityUITest2{

    @Test
    fun test_isActivityInView () {

        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.mainActivityLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun test_visibility_topAppBar_and_searchButton_and_viewPager() {

        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.topAppBar)).check(matches(isDisplayed()))
        onView(withId(R.id.search)).check(matches(isDisplayed()))
        onView(withId(R.id.viewPager)).check(matches(isDisplayed()))
    }


}