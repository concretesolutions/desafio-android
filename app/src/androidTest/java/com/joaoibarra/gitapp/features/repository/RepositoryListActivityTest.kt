package com.joaoibarra.gitapp.features.repository

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.joaoibarra.gitapp.R
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RepositoryListActivityTest {

    @get:Rule
    val repositoriesActivityRule = ActivityTestRule<RepositoryListActivity>(RepositoryListActivity::class.java, true, false)

    @Before
    fun setUp() {
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun checkRepoList() {
        val intent = Intent()
        repositoriesActivityRule.launchActivity(intent)
        onView(withId(R.id.recyclerRepositories)).check(matches(isDisplayed()))
    }
}