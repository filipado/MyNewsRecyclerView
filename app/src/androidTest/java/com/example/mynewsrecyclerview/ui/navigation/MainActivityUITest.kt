package com.example.mynewsrecyclerview.ui.navigation

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.mynewsrecyclerview.R
import com.example.mynewsrecyclerview.ui.MainActivity
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityUITest {

    @Test
    fun test_navigationSearchActivity() {

        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.search)).perform(click())

        onView(withId(R.id.searchActivityLayout)).check(matches(isDisplayed()))
    }


    @Test
    fun test_backPressToMainActivity() {

        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.search)).perform(click())

        onView(withId(R.id.searchActivityLayout)).check(matches(isDisplayed()))

        pressBack()

        onView(withId(R.id.mainActivityLayout)).check(matches(isDisplayed()))
    }
}