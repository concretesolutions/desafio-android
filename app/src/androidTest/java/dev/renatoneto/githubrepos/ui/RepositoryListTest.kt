package dev.renatoneto.githubrepos.ui

import android.content.Intent
import android.os.SystemClock
import android.view.View
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
import dev.renatoneto.githubrepos.ui.repositorydetails.RepositoryDetailsActivity
import dev.renatoneto.githubrepos.ui.repositorylist.RepositoryListActivity
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
class RepositoryListTest : BaseTest() {

    @get:Rule
    val rule = IntentsTestRule(
        RepositoryListActivity::class.java, true, false
    )

    @Test
    fun testSuccessLoadRepositories() {
        rule.launchActivity(Intent())

        enqueueMockServer(rule.activity, "repository_list.json")

        onView(withId(R.id.progressBar)).check(matches(isDisplayed()))

        SystemClock.sleep(300L)
        onView(allOf<View>(withId(R.id.txtRepositoryName), withText("java-design-patterns")))
            .check(matches(isDisplayed()))

        rule.finishActivity()
    }

    @Test
    fun testErrorLoadRepositories() {
        rule.launchActivity(Intent())

        enqueueMockServer(rule.activity, "repository_list.json", 404)

        SystemClock.sleep(300L)
        onView(withId(R.id.errorLayout)).check(matches(isDisplayed()))

        rule.finishActivity()
    }

    @Test
    fun testGoToRepositoryPullRequests() {
        rule.launchActivity(Intent())

        enqueueMockServer(rule.activity, "repository_list.json")

        SystemClock.sleep(300L)
        onView(withId(R.id.recyclerRepositories)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )

        intended(hasComponent(RepositoryDetailsActivity::class.java.name))

        rule.finishActivity()
    }

}
