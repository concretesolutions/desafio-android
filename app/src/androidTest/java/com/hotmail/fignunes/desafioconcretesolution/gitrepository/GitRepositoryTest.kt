package com.hotmail.fignunes.desafioconcretesolution.gitrepository

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.hotmail.fignunes.desafioconcretesolution.R
import com.hotmail.fignunes.desafioconcretesolution.presentation.gitrepository.GitRepositoryActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class GitRepositoryTest {

    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(GitRepositoryActivity::class.java)

    @Test
    fun fieldsVisible() {
        onView(withText(activityTestRule.activity.getString(R.string.git_hub_javapop))).check(matches(isDisplayed()))
        onView(withId(R.id.gitRepositoryRecyclerview)).check(matches(isDisplayed()))
        Thread.sleep(2000)
    }

    @Test
    fun swipeList() {
        Thread.sleep(3000)
        onView(withId(R.id.gitRepositoryRecyclerview)).perform(swipeUp())
        Thread.sleep(1000)
    }

    @Test
    fun callScreenPullRequest() {
        Thread.sleep(3000)
        onView(withId(R.id.gitRepositoryRecyclerview)).perform(click())

        Thread.sleep(2000)

        onView(withId(R.id.pullRequestOpened)).check(matches(isDisplayed()))
        onView(withId(R.id.pullRequestClosed)).check(matches(isDisplayed()))
    }

}