package com.example.mynewsrecyclerview.ui.activities

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.mynewsrecyclerview.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class SearchActivityTestUI {

    @get: Rule
    val activityRule = ActivityScenarioRule(SearchActivity::class.java)

    @Test
    fun test_isActivityInView() {

        onView(withId(R.id.searchActivityLayout))
            .check(matches(isDisplayed()))
    }

    @Test
    fun test_is_allUserInputFieldsVisible_visible() {

        // search query
        onView(withId(R.id.textInputSearchQuery)).check(matches(isDisplayed()))

        //start date
        onView(withId(R.id.datePickerStart)).check(matches(isDisplayed()))

        // end date
        onView(withId(R.id.datePickerEnd)).check(matches(isDisplayed()))

        // arts cb
        onView(withId(R.id.artsCB)).check(matches(isDisplayed()))

        // business cb
        onView(withId(R.id.businessCB)).check(matches(isDisplayed()))

        // entrepreneurs cb
        onView(withId(R.id.entrepreneursCB)).check(matches(isDisplayed()))

        // politics cb
        onView(withId(R.id.politicsCB)).check(matches(isDisplayed()))

        // sports cb
        onView(withId(R.id.sportsCB)).check(matches(isDisplayed()))

        // travel cb
        onView(withId(R.id.travelCB)).check(matches(isDisplayed()))

        // search button
        onView(withId(R.id.buttonSearch)).check(matches(isDisplayed()))
    }

    @Test
    fun test_is_searchButton_text_correctly_displayed () {

        onView(withId(R.id.buttonSearch)).check(matches(withText(R.string.search)))
    }
}