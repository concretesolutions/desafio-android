package com.mbstro.fmoyagonzalez.desafio_android


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
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
import android.support.v7.widget.RecyclerView
import android.support.test.espresso.matcher.BoundedMatcher


@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun swipeAndClickFirstRepo() {
        Thread.sleep(6000)
        val recyclerView = onView(
                allOf(withId(R.id.recycler_view),
                        childAtPosition(
                                withClassName(`is`("android.support.constraint.ConstraintLayout")),
                                0)))
        onView(withId(R.id.recycler_view))
                .check(matches(atPosition(0, withText("java-design-patterns"))))
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(0, click()))
        Thread.sleep(2000)
        pressBack()

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

    @Test
    fun clickPullRequest(){
        Thread.sleep(6000)
        val recyclerView = onView(
                allOf(withId(R.id.recycler_view),
                        childAtPosition(
                                withClassName(`is`("android.support.constraint.ConstraintLayout")),
                                0)))
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(0, click()))
        Thread.sleep(2000)

        val recyclerView2 = onView(
                allOf(withId(R.id.recycler_view_pull_request),
                        childAtPosition(
                                withClassName(`is`("android.support.constraint.ConstraintLayout")),
                                2)))

        recyclerView2.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

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

    private fun atPosition(position: Int, itemMatcher: Matcher<View>): Matcher<View> {
        checkNotNull(itemMatcher)
        return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has item at position $position: ")
                itemMatcher.describeTo(description)
            }

            override fun matchesSafely(view: RecyclerView): Boolean {
                val va = view.adapter?.itemCount

                val viewHolder = view.findViewHolderForAdapterPosition(position)
                        ?: // has no item on such position
                        return false
                val sr: TextView = viewHolder.itemView.findViewById(R.id.name_repo);
                return itemMatcher.matches(sr)
            }
        }
    }

    private fun count(itemMatcher: Matcher<Integer>): BoundedMatcher<View, RecyclerView> {
        checkNotNull(itemMatcher)
        return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has item at position : ")
                itemMatcher.describeTo(description)
            }

            override fun matchesSafely(view: RecyclerView): Boolean {
                val va = view.adapter?.itemCount
                return itemMatcher.matches(va)
            }
        }
    }
}
