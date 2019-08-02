package matheusuehara.github.features.repository

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import matheusuehara.github.R
import matheusuehara.github.base.BaseInstrumentedTest
import matheusuehara.github.extensions.waitUntil
import org.junit.Rule
import org.junit.Test

class RepositoryActivityTest : BaseInstrumentedTest() {

    @get:Rule
    val activityRule = ActivityTestRule(RepositoryActivity::class.java)

    @Test
    fun checkItemLoad() {
        mockResponse200("repository_return_success.json")
        onView(withId(R.id.shimmer_view_container)).check(matches(isDisplayed()))
    }

    @Test
    fun checkListSuccess() {
        mockResponse200("repository_return_success.json")
        onView(withId(R.id.rvRepositories))
                .perform(isDisplayed().waitUntil())
                .check(matches(isDisplayed()))
    }

    @Test
    fun checkListNetworkError() {
        mockResponseError404()
        onView(withId(R.id.snackbar_text))
                .perform(isDisplayed().waitUntil())
                .check(matches(withText(R.string.connection_error)))
    }

    @Test
    fun checkEmptyListError() {
        mockResponse200("return_empty_result.json")
        onView(withId(R.id.snackbar_text))
                .perform(isDisplayed().waitUntil())
                .check(matches(withText(R.string.empty_result)))
    }

}