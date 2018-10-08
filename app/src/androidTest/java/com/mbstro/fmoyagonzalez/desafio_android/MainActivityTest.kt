package com.mbstro.fmoyagonzalez.desafio_android


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.matcher.BoundedMatcher
import android.support.test.espresso.matcher.ViewMatchers.withClassName
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {
        val recyclerView = onView(
                allOf(withId(R.id.recycler_view),
                        childAtPosition(
                                withClassName(`is`("android.support.constraint.ConstraintLayout")),
                                0)))
        Thread.sleep(6000)
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(0, click()))
        Thread.sleep(2000)
        pressBack()

        val recyclerView2 = onView(
                allOf(withId(R.id.recycler_view_pull_request),
                        childAtPosition(
                                withClassName(`is`("android.support.constraint.ConstraintLayout")),
                                2)))

        //recyclerView2.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        recyclerView.perform(ViewActions.swipeUp())
        Thread.sleep(1000)
        recyclerView.perform(ViewActions.swipeUp())
        Thread.sleep(1000)
        recyclerView.perform(ViewActions.swipeUp())
        Thread.sleep(1000)
        recyclerView.perform(ViewActions.swipeUp())
        Thread.sleep(1000)
        recyclerView.perform(ViewActions.swipeUp())
        Thread.sleep(6000)
        recyclerView.perform(ViewActions.swipeUp())

    }

    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                parent is ViewGroup
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }

    fun isInTheMiddle(text : String): Matcher<RepoAdapter.ViewHolder> {
        return object : TypeSafeMatcher<RepoAdapter.ViewHolder>() {
            override fun matchesSafely(item: RepoAdapter.ViewHolder ): Boolean {
                val nameRepo = item.itemView.findViewById(R.id.name_repo) as TextView
                        ?: return false
                return nameRepo.text.toString().contains(text)
            }

            override fun describeTo(description: Description) {
                description.appendText("first item with repo name ")
            }
        }
    }


}
