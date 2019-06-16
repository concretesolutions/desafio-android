package com.abs.javarepos.view

import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.abs.javarepos.R
import com.abs.javarepos.model.Owner
import com.abs.javarepos.model.Repo
import com.abs.javarepos.view.adapter.RepoAdapter
import org.junit.Rule
import org.junit.Test


class ReposActivityTest {

    @get:Rule
    val reposActivity = ActivityScenarioRule(ReposActivity::class.java)
    val repo = Repo(
        "Name",
        "Description",
        Owner(
            "Login",
            "https://randomuser.me/api/portraits/med/men/0.jpg"
        ),
        123,
        456
    )

    @Test
    fun whenResumed_thenShowTitle() {
        reposActivity.scenario.moveToState(Lifecycle.State.RESUMED)
        onView(withId(R.id.action_bar))
            .check(matches(withChild(withText(R.string.repos_title))))
            .check(matches(isDisplayed()))
    }

    @Test
    fun whenResumed_thenShowList() {
        reposActivity.scenario.moveToState(Lifecycle.State.RESUMED)
        onView(withId(R.id.rvRepos))
            .check(matches(isDisplayed()))
    }

    @Test
    fun givenResumed_whenAddItems_thenShowRepoName() {
        reposActivity.scenario.moveToState(Lifecycle.State.RESUMED)
        reposActivity.scenario.onActivity { activity ->
            activity.repoAdapter.addItems(arrayListOf(repo))
        }
        onView(withId(R.id.rvRepos)).perform(actionOnItemAtPosition<RepoAdapter.ViewHolder>(0, scrollTo()))
        onView(withId(R.id.tvName)).check(matches(isDisplayed()))
    }

    @Test
    fun givenResumed_whenAddItems_thenShowRepoDescription() {
        reposActivity.scenario.moveToState(Lifecycle.State.RESUMED)
        reposActivity.scenario.onActivity { activity ->
            activity.repoAdapter.addItems(arrayListOf(repo))
        }
        onView(withId(R.id.rvRepos)).perform(actionOnItemAtPosition<RepoAdapter.ViewHolder>(0, scrollTo()))
        onView(withId(R.id.tvDescription)).check(matches(isDisplayed()))
    }

    @Test
    fun givenResumed_whenAddItems_thenShowRepoOwnerName() {
        reposActivity.scenario.moveToState(Lifecycle.State.RESUMED)
        reposActivity.scenario.onActivity { activity ->
            activity.repoAdapter.addItems(arrayListOf(repo))
        }
        onView(withId(R.id.rvRepos)).perform(actionOnItemAtPosition<RepoAdapter.ViewHolder>(0, scrollTo()))
        onView(withId(R.id.tvOwnerName)).check(matches(isDisplayed()))
    }

    @Test
    fun givenResumed_whenAddItems_thenShowRepoOwnerPicture() {
        reposActivity.scenario.moveToState(Lifecycle.State.RESUMED)
        reposActivity.scenario.onActivity { activity ->
            activity.repoAdapter.addItems(arrayListOf(repo))
        }
        onView(withId(R.id.rvRepos)).perform(actionOnItemAtPosition<RepoAdapter.ViewHolder>(0, scrollTo()))
        onView(withId(R.id.ivOwnerPicture)).check(matches(isDisplayed()))

    }

    @Test
    fun givenResumed_whenAddItems_thenShowRepoStars() {
        reposActivity.scenario.moveToState(Lifecycle.State.RESUMED)
        reposActivity.scenario.onActivity { activity ->
            activity.repoAdapter.addItems(arrayListOf(repo))
        }
        onView(withId(R.id.rvRepos)).perform(actionOnItemAtPosition<RepoAdapter.ViewHolder>(0, scrollTo()))
        onView(withId(R.id.tvStars)).check(matches(isDisplayed()))
    }

    @Test
    fun givenResumed_whenAddItems_thenShowRepoForks() {
        reposActivity.scenario.moveToState(Lifecycle.State.RESUMED)
        reposActivity.scenario.onActivity { activity ->
            activity.repoAdapter.addItems(arrayListOf(repo))
        }
        onView(withId(R.id.rvRepos)).perform(actionOnItemAtPosition<RepoAdapter.ViewHolder>(0, scrollTo()))
        onView(withId(R.id.tvForks)).check(matches(isDisplayed()))
    }
}