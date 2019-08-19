package dev.theuzfaleiro.trendingongithub.ui.feature.home

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
import dev.theuzfaleiro.trendingongithub.ui.feature.pullrequest.PULL_REQUEST_OWNER_NAME
import dev.theuzfaleiro.trendingongithub.ui.feature.pullrequest.PULL_REQUEST_REPOSITORY_NAME
import dev.theuzfaleiro.trendingongithub.ui.feature.pullrequest.PullRequestActivity
import io.appflate.restmock.RESTMockServer
import io.appflate.restmock.utils.RequestMatchers.pathContains
import org.hamcrest.CoreMatchers.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {

    @get:Rule
    val homeActivity = IntentsTestRule(HomeActivity::class.java, true, false)

    @Before
    fun setUp() {
        RESTMockServer.reset()
    }

    @Test
    fun shouldDisplayRepositories_WhenFetchRepositoriesFromAPI() {
        RESTMockServer.whenGET(pathContains("repositories"))
            .thenReturnFile(200, "repository/repositories-list.json")

        homeActivity.launchActivity(Intent())

        onView(withText("android-architecture")).check(matches(isDisplayed()))
    }

    @Test
    fun shouldDisplayRetryMessage_WhenFetchRepositoriesFromAPI() {
        RESTMockServer.whenGET(pathContains("repositories"))
            .thenReturnEmpty(404)

        homeActivity.launchActivity(Intent())

        onView(withText("android-architecture")).check(matches(isDisplayed()))
    }

    @Test
    fun shouldScrollToAPosition_WhenFetchRepositoriesFromAPI() {
        RESTMockServer.whenGET(pathContains("repositories"))
            .thenReturnFile(200, "repository/repositories-list.json")

        homeActivity.launchActivity(Intent())

        onView(withId(R.id.recyclerViewRepository)).perform(
            scrollToPosition<RecyclerView.ViewHolder>(
                15
            )
        )
    }

    @Test
    fun shouldOpenPullRequestList_WhenARepositoryWasSelected() {
        RESTMockServer.whenGET(pathContains("repositories"))
            .thenReturnFile(200, "repository/repositories-list.json")

        homeActivity.launchActivity(
            Intent().putExtras(
                bundleOf(
                    PULL_REQUEST_REPOSITORY_NAME to "minimal-weather",
                    PULL_REQUEST_OWNER_NAME to "theuzfaleiro"
                )
            )
        )

        val activityResult = Instrumentation.ActivityResult(Activity.RESULT_OK, Intent())

        Intents.intending(IntentMatchers.hasComponent(PullRequestActivity::class.java.name))
            .respondWith(activityResult)

        onView(withId(R.id.recyclerViewRepository)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        Intents.intended(
            allOf(
                IntentMatchers.hasComponent(PullRequestActivity::class.java.name),
                IntentMatchers.hasExtraWithKey(PULL_REQUEST_REPOSITORY_NAME)
            )
        )
    }
}
