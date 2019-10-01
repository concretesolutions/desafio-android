package br.com.guilherme.solution

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import br.com.guilherme.solution.ui.pullRequests.PullRequestsActivity
import br.com.guilherme.solution.ui.repositoryList.MainActivity
import br.com.guilherme.solution.ui.repositoryList.RepositoryAdapter
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PullRequestInstrumentedTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule<PullRequestsActivity>(
        PullRequestsActivity::class.java
    )

    @Test
    fun checkClick() {
        Espresso.onView(ViewMatchers.withId(R.id.recycler_view_pull_request))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.recycler_view_pull_request)).perform(
            RecyclerViewActions
                .actionOnItemAtPosition<RepositoryAdapter.ViewHolder>(0, ViewActions.click())
        )
    }
}