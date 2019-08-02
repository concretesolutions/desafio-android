package matheusuehara.github.features.repository

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import matheusuehara.github.R
import matheusuehara.github.base.BaseInstrumentedTest
import matheusuehara.github.extensions.childAtPosition
import matheusuehara.github.extensions.waitUntil
import org.junit.Rule
import org.junit.Test

class RepositoryActivityTest : BaseInstrumentedTest() {

    @get:Rule
    val activityRule = ActivityTestRule(RepositoryActivity::class.java)

    @Test
    fun checkLoadListSuccess() {
        mockResponse200("repository_return_success.json")

        Espresso.onView(ViewMatchers.withId(R.id.rvRepositories))
                .perform(ViewMatchers.isDisplayed().waitUntil())
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun checkLoadFirstItemSuccess() {
        mockResponse200("repository_return_success.json")
        Espresso.onView(ViewMatchers.withId(R.id.rvRepositories)
                .childAtPosition(0)
                .childAtPosition(0)
                .childAtPosition(0)
                .childAtPosition(1))
                .perform(ViewMatchers.isDisplayed().waitUntil())
                .check(ViewAssertions.matches(ViewMatchers.withText("android-achitecture")))
    }
}