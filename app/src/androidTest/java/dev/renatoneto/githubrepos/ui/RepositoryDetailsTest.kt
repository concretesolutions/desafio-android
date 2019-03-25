package dev.renatoneto.githubrepos.ui

import android.content.Intent
import android.os.SystemClock
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.contrib.RecyclerViewActions.*
import androidx.test.espresso.intent.Intents.*
import androidx.test.espresso.intent.matcher.IntentMatchers.*
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.runner.AndroidJUnit4
import dev.renatoneto.githubrepos.R
import dev.renatoneto.githubrepos.base.BaseTest
import dev.renatoneto.githubrepos.model.github.GithubRepository
import dev.renatoneto.githubrepos.model.github.GithubUser
import dev.renatoneto.githubrepos.ui.repositorydetails.RepositoryDetailsActivity
import org.hamcrest.CoreMatchers.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class RepositoryDetailsTest : BaseTest() {

    @get:Rule
    val rule = IntentsTestRule(
        RepositoryDetailsActivity::class.java, true, false
    )

    private fun launchActivity() {
        val intent = Intent()
        intent.putExtra(Arguments.ARG_GITHUB_REPOSITORY, GithubRepository(
            "repository name",
            GithubUser("user", "avatar"),
            "description",
            10,
            11
        ))
        rule.launchActivity(intent)
    }

    @Test
    fun testSuccessLoadPullRequests() {
        launchActivity()

        enqueueMockServer(rule.activity, "repository_pull_requests_list.json")

        onView(withId(R.id.progressBar)).check(matches(isDisplayed()))

        onView(allOf(instanceOf(TextView::class.java), withParent(withResourceName("action_bar"))))
            .check(matches(withText("repository name")))

        SystemClock.sleep(300L)
        onView(allOf<View>(withId(R.id.txtPullRequestTitle), withText("Minor ReadMe Update")))
            .check(matches(isDisplayed()))

        rule.finishActivity()
    }

    @Test
    fun testErrorLoadPullRequests() {
        launchActivity()

        enqueueMockServer(rule.activity, "repository_pull_requests_list.json", 404)

        SystemClock.sleep(300L)
        onView(withId(R.id.errorLayout)).check(matches(isDisplayed()))

        rule.finishActivity()
    }

    @Test
    fun testLoadEmptyPullRequests() {
        launchActivity()

        enqueueMockServer(rule.activity, "repository_pull_requests_empty_list.json", 404)

        SystemClock.sleep(300L)
        onView(withId(R.id.errorLayout)).check(matches(isDisplayed()))

        rule.finishActivity()
    }

    @Test
    fun testGoToRepositoryPullRequestDetails() {
        launchActivity()

        enqueueMockServer(rule.activity, "repository_pull_requests_list.json")

        SystemClock.sleep(300L)
        onView(withId(R.id.recyclerPullRequests)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )

        intended(
            allOf(hasAction(Intent.ACTION_VIEW), hasData("https://github.com/iluwatar/java-design-patterns/pull/864"))
        )

        rule.finishActivity()
    }

}
