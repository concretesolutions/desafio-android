package com.example.lucasdias.mvvmpoc

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.runner.RunWith
import com.example.lucasdias.mvvmpoc.presentation.ui.repositories.RepositoryActivity
import android.support.test.rule.ActivityTestRule
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.matcher.ViewMatchers
import android.support.v7.widget.RecyclerView

import org.junit.Test


@RunWith(AndroidJUnit4::class)
class RepositoryActivityUITest {

    @get:Rule
    var activityTestRule = ActivityTestRule(RepositoryActivity::class.java)

    @Test
    fun onRepositoryClick() {
//        Espresso.onView(ViewMatchers.withId(R.id.repositoryActivity_rvRepositoryList))
//                .perform(RecyclerViewActions
//                        .actionOnItemAtPosition<RecyclerView.ViewHolder>(1, ViewActions.click()))

//        intended(hasComponent(
//                "com.example.lucasdias.mvvmpoc.presentation.ui.pullrequests.PullRequestActivity"))
    }
}