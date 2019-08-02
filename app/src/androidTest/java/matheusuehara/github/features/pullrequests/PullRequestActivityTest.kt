package matheusuehara.github.features.pullrequests

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import matheusuehara.github.base.BaseInstrumentedTest
import matheusuehara.github.extensions.waitUntil
import matheusuehara.github.util.Constans.INTENT_REPOSITORY_NAME
import matheusuehara.github.util.Constans.INTENT_REPOSITORY_OWNER_NAME
import org.junit.Rule
import org.junit.Test
import android.widget.TextView
import matheusuehara.github.R
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.core.AllOf.allOf
import androidx.test.espresso.matcher.ViewMatchers.withParent
import org.hamcrest.CoreMatchers.instanceOf

class PullRequestActivityTest : BaseInstrumentedTest() {

    @get:Rule
    val mActivityTestRule: ActivityTestRule<PullRequestActivity> =
            object : ActivityTestRule<PullRequestActivity>(PullRequestActivity::class.java) {
                override fun getActivityIntent(): Intent {
                    val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
                    return Intent(targetContext, PullRequestActivity::class.java).apply {
                        putExtra(INTENT_REPOSITORY_NAME, "android-architecture")
                        putExtra(INTENT_REPOSITORY_OWNER_NAME, "googlesamples")
                    }
                }
            }

    @Test
    fun checkToolbarTitle() {
        val toolbarTitile = "android-architecture"
        onView(allOf(instanceOf(TextView::class.java),
                withParent(withResourceName("action_bar"))))
                .check(matches(withText(toolbarTitile)))
    }

    @Test
    fun checkListSuccess() {
        mockResponse200("pull_request_return_success.json")
        onView(withId(R.id.rvPullRequest))
                .perform(isDisplayed().waitUntil())
                .check(matches(isDisplayed()))
    }

    @Test
    fun checkLoadEmptyList() {
        mockResponse200("return_empty_result.json")
        onView(withId(R.id.snackbar_text))
                .perform(isDisplayed().waitUntil())
                .check(matches(withText(R.string.empty_result)))
    }

    @Test
    fun checkLoadNetworkError() {
        mockResponseError404()
        onView(withId(R.id.snackbar_text))
                .perform(isDisplayed().waitUntil())
                .check(matches(withText(R.string.connection_error)))
    }

    @Test
    fun checkItemLoad() {
        mockResponse200("pull_request_return_success.json")
        onView(withId(R.id.shimmer_view_container)).check(matches(isDisplayed()))
    }


}