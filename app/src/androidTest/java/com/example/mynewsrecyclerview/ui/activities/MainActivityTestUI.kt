package com.example.mynewsrecyclerview.ui.activities

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.mynewsrecyclerview.R
import com.example.mynewsrecyclerview.ui.MainActivity
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTestUI{

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


    // check title on the top app bar but this isn't working
    // test fails, there is not text field in .xml file of main activity's topAppBar -
    // there is .xml title field which is not detectable with the use
    // of R.id.topAppBar id

    //maybe come back to this later

    /*

    @Test
    fun test_isTitleTextDisplayed () {

        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.topAppBar)).check(matches(withText(R.string.app_name)))
    }

     */


}