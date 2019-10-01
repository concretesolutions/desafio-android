package br.com.guilherme.solution

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import br.com.guilherme.solution.ui.repositoryList.MainActivity
import br.com.guilherme.solution.ui.repositoryList.RepositoryAdapter
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainInstrumentedTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule<MainActivity>(
        MainActivity::class.java
    )

    @Test
    fun checkClick() {
        onView(ViewMatchers.withId(R.id.recycler_view_repositories))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.recycler_view_repositories)).perform(
            RecyclerViewActions
                .actionOnItemAtPosition<RepositoryAdapter.ViewHolder>(0, ViewActions.click())
        )
    }
}