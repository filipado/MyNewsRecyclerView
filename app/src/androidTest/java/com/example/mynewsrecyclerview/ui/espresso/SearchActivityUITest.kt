package com.example.mynewsrecyclerview.ui.espresso


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.example.mynewsrecyclerview.R
import com.example.mynewsrecyclerview.ui.activities.SearchActivity
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class SearchActivityUITest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(SearchActivity::class.java)

    @Test
    fun searchActivityUITest() {

        val textInputEditText = onView(
            allOf(
                withId(R.id.textInputSearchQuery),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.searchQueryTextFieldLayout),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText.perform(replaceText("obama"), closeSoftKeyboard())

        val textInputEditText2 = onView(
            allOf(
                withId(R.id.datePickerStart),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.datePickerStartLayout),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textInputEditText2.perform(click())

        val materialButton = onView(
            allOf(
                withId(android.R.id.button1), withText("OK"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    3
                )
            )
        )
        materialButton.perform(scrollTo(), click())

        val textInputEditText3 = onView(
            allOf(
                withId(R.id.datePickerEnd),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.datePickerEndLayout),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textInputEditText3.perform(click())

        val materialButton2 = onView(
            allOf(
                withId(android.R.id.button1), withText("OK"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    3
                )
            )
        )
        materialButton2.perform(scrollTo(), click())

        val materialCheckBox = onView(
            allOf(
                withId(R.id.artsCB), withText("Arts"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        materialCheckBox.perform(click())

        val materialCheckBox2 = onView(
            allOf(
                withId(R.id.politicsCB), withText("Politics"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    7
                ),
                isDisplayed()
            )
        )
        materialCheckBox2.perform(click())

        val materialCheckBox3 = onView(
            allOf(
                withId(R.id.businessCB), withText("Business"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    5
                ),
                isDisplayed()
            )
        )
        materialCheckBox3.perform(click())

        val materialCheckBox4 = onView(
            allOf(
                withId(R.id.sportsCB), withText("Sports"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    8
                ),
                isDisplayed()
            )
        )
        materialCheckBox4.perform(click())

        val materialCheckBox5 = onView(
            allOf(
                withId(R.id.entrepreneursCB), withText("Entrepreneurs"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    6
                ),
                isDisplayed()
            )
        )
        materialCheckBox5.perform(click())

        val materialCheckBox6 = onView(
            allOf(
                withId(R.id.travelCB), withText("Travel"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    9
                ),
                isDisplayed()
            )
        )
        materialCheckBox6.perform(click())

        val materialButton3 = onView(
            allOf(
                withId(R.id.buttonSearch), withText("Search"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    10
                ),
                isDisplayed()
            )
        )
        materialButton3.perform(click())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
