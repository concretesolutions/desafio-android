package com.abs.javarepos

import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.abs.javarepos.model.Repo
import com.abs.javarepos.view.RepoAdapter
import com.abs.javarepos.view.ReposActivity
import org.junit.Rule
import org.junit.Test


class ReposActivityTest {

    @get:Rule
    val reposActivity = ActivityScenarioRule(ReposActivity::class.java)
    val repo = Repo("Name", "Description")

    @Test
    fun whenResumedThenShowTitle() {
        reposActivity.scenario.moveToState(Lifecycle.State.RESUMED)
        onView(withId(R.id.action_bar))
            .check(matches(withChild(withText(R.string.repos_title))))
            .check(matches(isDisplayed()))
    }

    @Test
    fun whenResumedThenShowList() {
        reposActivity.scenario.moveToState(Lifecycle.State.RESUMED)
        onView(withId(R.id.rvRepos))
            .check(matches(isDisplayed()))
    }

    @Test
    fun givenResumedWhenAddItemsThenShowRepoName() {
        reposActivity.scenario.moveToState(Lifecycle.State.RESUMED)
        reposActivity.scenario.onActivity { activity ->
            activity.repoAdapter.addItems(arrayListOf(repo))
        }
        onView(withId(R.id.rvRepos)).perform(actionOnItemAtPosition<RepoAdapter.ViewHolder>(0, scrollTo()))
        onView(withId(R.id.tvName)).check(matches(isDisplayed()))
    }

    @Test
    fun givenResumedWhenAddItemsThenShowRepoDescription() {
        reposActivity.scenario.moveToState(Lifecycle.State.RESUMED)
        reposActivity.scenario.onActivity { activity ->
            activity.repoAdapter.addItems(arrayListOf(repo))
        }
        onView(withId(R.id.rvRepos)).perform(actionOnItemAtPosition<RepoAdapter.ViewHolder>(0, scrollTo()))
        onView(withId(R.id.tvDescription)).check(matches(isDisplayed()))
    }
}