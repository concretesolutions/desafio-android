package com.abs.javarepos

import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.abs.javarepos.view.ReposActivity
import org.junit.Rule
import org.junit.Test

class ReposActivityTest {

    @get:Rule
    val reposActivity = ActivityScenarioRule(ReposActivity::class.java)

    @Test
    fun whenResumedThenShowTitle() {
        reposActivity.scenario.moveToState(Lifecycle.State.RESUMED)
        onView(withId(R.id.action_bar))
            .check(matches(withChild(withText(R.string.repos_title))))
            .check(matches(isDisplayed()))
    }
}