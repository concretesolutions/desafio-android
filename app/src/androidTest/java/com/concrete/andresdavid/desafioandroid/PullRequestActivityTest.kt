package com.concrete.andresdavid.desafioandroid


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import android.view.ViewGroup
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import android.content.Intent
import android.support.test.espresso.action.ViewActions.click


@LargeTest
@RunWith(AndroidJUnit4::class)
class PullRequestActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(PullRequestsActivity::class.java, false, false)

    @Test
    fun repoGetPullRequest() {
        val repositoryName = "java-design-patterns"
        val repositoryUser = "iluwatar"

        val i = Intent()
        i.putExtra(MainActivity.REPOSITORY_NAME_KEY, repositoryName)
        i.putExtra(MainActivity.REPOSITORY_FULL_NAME_KEY, "$repositoryUser/$repositoryName")
        mActivityTestRule.launchActivity(i)

        Thread.sleep(4000)

        // display repo title
        onView(withText(repositoryName)).check(matches(isDisplayed()))

        // display pull request title
        val textView = onView(
                allOf(withId(R.id.tv_pr_title),
                        childAtPosition(
                                allOf(withResourceName("ly_pr_item"),
                                        childAtPosition(
                                                withResourceName("rv_list_pullrequest"),
                                                0)),
                                0)))
        textView.check(matches(isDisplayed()))

        // display pull request body
        val textView1 = onView(
                allOf(withId(R.id.tv_pr_body),
                        childAtPosition(
                                allOf(withResourceName("ly_pr_item"),
                                        childAtPosition(
                                                withResourceName("rv_list_pullrequest"),
                                                0)),
                                1)))
        textView1.check(matches(isDisplayed()))

        // display pull request user image
        val textView2 = onView(
                allOf(withId(R.id.image_pr_user),
                        childAtPosition(
                                allOf(withResourceName("ly_pr_item"),
                                        childAtPosition(
                                                withResourceName("rv_list_pullrequest"),
                                                0)),
                                2)))
        textView2.check(matches(isDisplayed()))

        // display pull request user name
        val textView3 = onView(
                allOf(withId(R.id.tv_pr_user),
                        childAtPosition(
                                allOf(withResourceName("ly_pr_item"),
                                        childAtPosition(
                                                withResourceName("rv_list_pullrequest"),
                                                0)),
                                3)))
        textView3.check(matches(isDisplayed()))
    }

    @Test
    fun checkPullRequestLink() {
        val repositoryName = "java-design-patterns"
        val repositoryUser = "iluwatar"

        val i = Intent()
        i.putExtra(MainActivity.REPOSITORY_NAME_KEY, repositoryName)
        i.putExtra(MainActivity.REPOSITORY_FULL_NAME_KEY, "$repositoryUser/$repositoryName")
        mActivityTestRule.launchActivity(i)

        Thread.sleep(4000)

        // click on first pull request
        val listItem = onView(
                                allOf(withResourceName("ly_pr_item"),
                                        childAtPosition(
                                                withResourceName("rv_list_pullrequest"),
                                                0))
                                )
        listItem.perform(click())


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
