package dev.theuzfaleiro.trendingongithub.ui.feature.pullrequest

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
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
        //arrange
        RESTMockServer.whenGET(pathContains("repos"))
            .thenReturnFile(200, "pullrequest/pull-requests.json")

        //act
        pullRequestActivity.launchActivity(Intent())

        //assert
        onView(withText("Mention the `master` branch as the target of pull requests in contrib…"))
            .check(matches(isDisplayed()))
    }

    @Test
    fun shouldDisplayGoBackButton_WhenFetchNoOpenPullRequestsFromAPI() {
        //arrange
        RESTMockServer.whenGET(pathContains("repos"))
            .thenReturnString(200, "[]")

        //act
        pullRequestActivity.launchActivity(Intent())

        //assert
        onView(withText(R.string.pull_request_title_any_open_pull_requests))
            .check(matches(isDisplayed()))

        onView(withText(R.string.pull_request_message_any_open_pull_requests))
            .check(matches(isDisplayed()))

        onView(withId(R.id.buttonPullRequestGoBack))
            .check(matches(isDisplayed()))
    }

    @Test
    fun shouldDisplayRetryMessage_WhenFetchOpenPullRequestsFromAPI() {
        //arrange
        RESTMockServer.whenGET(pathContains("repos"))
            .thenReturnEmpty(404)

        //act
        pullRequestActivity.launchActivity(Intent())

        //assert
        onView(withText(R.string.pull_request_title_error_no_internet_connection))
            .check(matches(isDisplayed()))

        onView(withText(R.string.pull_request_message_check_your_mobile_data_or_wi_fi))
            .check(matches(isDisplayed()))

        onView(withId(R.id.buttonPullRequestRetry))
            .check(matches(isDisplayed()))
    }

    @Test
    fun shouldDisplayPullRequests_WhenRetryButtonWasClicked() {
        //arrange
        RESTMockServer.whenGET(pathContains("repos"))
            .thenReturnEmpty(404)
            .thenReturnFile(200, "pullrequest/pull-requests.json")

        //act
        pullRequestActivity.launchActivity(Intent())

        //assert
        onView(withId(R.id.buttonPullRequestRetry))
            .check(matches(isDisplayed()))
            .perform(click())

        onView(withText("Mention the `master` branch as the target of pull requests in contrib…"))
            .check(matches(isDisplayed()))
    }

    @Test
    fun shouldScrollToAPosition_WhenFetchRepositoriesFromAPI() {
        //arrange
        RESTMockServer.whenGET(pathContains("repos"))
            .thenReturnFile(200, "pullrequest/pull-requests.json")

        //assert
        pullRequestActivity.launchActivity(Intent())

        //act
        onView(withId(R.id.recyclerViewPullRequest)).perform(
            scrollToPosition<RecyclerView.ViewHolder>(15)
        )
    }

    @Test
    fun shouldOpenPullRequestDetail_WhenAPullRequestWasSelected() {
        //arrange
        RESTMockServer.whenGET(pathContains("repos"))
            .thenReturnFile(200, "pullrequest/pull-requests.json")

        //act
        pullRequestActivity.launchActivity(
            Intent().putExtras(
                bundleOf(
                    PULL_REQUEST_URL to "https://bit.ly"
                )
            )
        )

        val activityResult = Instrumentation.ActivityResult(Activity.RESULT_OK, Intent())

        //assert
        Intents.intending(IntentMatchers.hasComponent(PullRequestDetailActivity::class.java.name))
            .respondWith(activityResult)

        onView(withId(R.id.recyclerViewPullRequest)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
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