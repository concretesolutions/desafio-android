package dev.theuzfaleiro.trendingongithub.ui.feature.pullrequest

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import dev.theuzfaleiro.trendingongithub.R
import dev.theuzfaleiro.trendingongithub.ui.feature.pullrequestdetail.PULL_REQUEST_URL
import dev.theuzfaleiro.trendingongithub.ui.feature.pullrequestdetail.PullRequestDetailActivity
import io.appflate.restmock.RESTMockServer
import io.appflate.restmock.utils.RequestMatchers.pathContains
import org.hamcrest.CoreMatchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PullRequestActivityTest {

    @get:Rule
    val pullRequestActivity = IntentsTestRule(PullRequestActivity::class.java, true, false)

    @Before
    fun setUp() {
        RESTMockServer.reset()
    }

    @Test
    fun shouldDisplayOpenPullRequests_WhenFetchOpenPullRequestsFromAPI() {
        RESTMockServer.whenGET(pathContains("repos"))
            .thenReturnFile(200, "pullrequest/pull-requests.json")

        pullRequestActivity.launchActivity(Intent())

        onView(withText("Mention the `master` branch as the target of pull requests in contrib…"))
            .check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun shouldDisplayRetryMessage_WhenFetchRepositoriesFromAPI() {
        RESTMockServer.whenGET(pathContains("repos"))
            .thenReturnEmpty(404)

        pullRequestActivity.launchActivity(Intent())

        onView(withText("Mention the `master` branch as the target of pull requests in contrib…"))
            .check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun shouldScrollToAPosition_WhenFetchRepositoriesFromAPI() {
        RESTMockServer.whenGET(pathContains("repos"))
            .thenReturnFile(200, "pullrequest/pull-requests.json")

        pullRequestActivity.launchActivity(Intent())

        onView(ViewMatchers.withId(R.id.recyclerViewPullRequest)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                15
            )
        )
    }

    @Test
    fun shouldOpenPullRequestDetail_WhenAPullRequestWasSelected() {
        RESTMockServer.whenGET(pathContains("repos"))
            .thenReturnFile(200, "pullrequest/pull-requests.json")

        pullRequestActivity.launchActivity(
            Intent().putExtras(
                bundleOf(
                    PULL_REQUEST_URL to "https://bit.ly"
                )
            )
        )

        val activityResult = Instrumentation.ActivityResult(Activity.RESULT_OK, Intent())

        Intents.intending(IntentMatchers.hasComponent(PullRequestDetailActivity::class.java.name))
            .respondWith(activityResult)

        onView(ViewMatchers.withId(R.id.recyclerViewPullRequest)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )

        Intents.intended(
            CoreMatchers.allOf(
                IntentMatchers.hasComponent(PullRequestDetailActivity::class.java.name),
                IntentMatchers.hasExtraWithKey(PULL_REQUEST_URL)
            )
        )
    }
}