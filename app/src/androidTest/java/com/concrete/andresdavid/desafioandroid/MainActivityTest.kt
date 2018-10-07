package com.concrete.andresdavid.desafioandroid


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.concrete.andresdavid.desafioandroid.adapters.RepositoryHolder
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Assert
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
    fun mainActivityInfoTest() {
        // display loading item
        onView(withId(R.id.pb_loading)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_loading)).check(matches(isDisplayed()))

        Thread.sleep(4000)

        val recyclerView = mActivityTestRule.activity.findViewById(R.id.rv_list_repository) as RecyclerView
        Assert.assertTrue(recyclerView.adapter?.itemCount!! > 0)

        // display repository name
        val textView = onView(
                allOf(withId(R.id.tv_repository_name),
                        childAtPosition(
                                allOf(withResourceName("ly_repository_item"),
                                        childAtPosition(
                                                withResourceName("rv_list_repository"),
                                                0)),
                                0)))
        textView.check(matches(isDisplayed()))

        // display repository description
        val textView1 = onView(
                allOf(withId(R.id.tv_repository_description),
                        childAtPosition(
                                allOf(withResourceName("ly_repository_item"),
                                        childAtPosition(
                                                withResourceName("rv_list_repository"),
                                                0)),
                                1)))
        textView1.check(matches(isDisplayed()))

        // display fork count
        val textView2 = onView(
                allOf(withId(R.id.tv_fork_count),
                        childAtPosition(
                                allOf(withResourceName("ly_repository_item"),
                                        childAtPosition(
                                                withResourceName("rv_list_repository"),
                                                0)),
                                3)))
        textView2.check(matches(isDisplayed()))

        // display stars count
        val textView3 = onView(
                allOf(withId(R.id.tv_stars_count),
                        childAtPosition(
                                allOf(withResourceName("ly_repository_item"),
                                        childAtPosition(
                                                withResourceName("rv_list_repository"),
                                                0)),
                                5)))
        textView3.check(matches(isDisplayed()))

        // display user image
        val textView4 = onView(
                allOf(withId(R.id.image_user),
                        childAtPosition(
                                allOf(withResourceName("ly_repository_item"),
                                        childAtPosition(
                                                withResourceName("rv_list_repository"),
                                                0)),
                                6)))
        textView4.check(matches(isDisplayed()))

        // display user login
        val textView5 = onView(
                allOf(withId(R.id.tv_user_name),
                        childAtPosition(
                                allOf(withResourceName("ly_repository_item"),
                                        childAtPosition(
                                                withResourceName("rv_list_repository"),
                                                0)),
                                7)))
        textView5.check(matches(isDisplayed()))
    }

    @Test
    fun infiniteScrollTest() {
        // wait to load first items
        Thread.sleep(4000)

        // scrolls to last item
        val recyclerView = mActivityTestRule.activity.findViewById(R.id.rv_list_repository) as RecyclerView
        val lastItemPosition: Int = recyclerView.adapter?.itemCount!! - 1

        onView(withId(R.id.rv_list_repository))
                .perform(RecyclerViewActions.scrollToPosition<RepositoryHolder>(lastItemPosition))

        Thread.sleep(4000)

        val newlastItemPosition: Int = recyclerView.adapter?.itemCount!! - 1

        onView(withId(R.id.rv_list_repository))
                .perform(RecyclerViewActions.scrollToPosition<RepositoryHolder>(newlastItemPosition))

        Thread.sleep(4000)

        val new2lastItemPosition: Int = recyclerView.adapter?.itemCount!! - 1

        onView(withId(R.id.rv_list_repository))
                .perform(RecyclerViewActions.scrollToPosition<RepositoryHolder>(new2lastItemPosition))
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
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
