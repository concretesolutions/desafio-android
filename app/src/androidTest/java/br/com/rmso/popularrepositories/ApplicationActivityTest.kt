package br.com.rmso.popularrepositories

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressBack
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.uiautomator.UiDevice
import br.com.rmso.popularrepositories.ui.repository.RepositoryActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class ApplicationActivityTest{

    private val itemFindPosition = 3
    private val itemTitle = "LeetCodeAnimation"

    @get:Rule
    val activityRepositoryRule = ActivityTestRule(RepositoryActivity::class.java)

    @Test
    fun clickPositionTest() {
        Espresso.onView(withId(R.id.rv_repository)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
    }

    @Test
    fun scrollTest() {
        val recyclerView: RecyclerView = activityRepositoryRule.activity.findViewById(R.id.rv_repository)
        val itemCount = recyclerView.adapter?.itemCount as Int

        Espresso.onView(withId(R.id.rv_repository)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(itemCount - 3))
    }

    @Test
    fun checkTitleItemTest() {
        Espresso.onView(withId(R.id.rv_repository)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(itemFindPosition, click()))
        Espresso.onView(withText(itemTitle)).check(matches(isDisplayed()))
    }

    @Test
    fun openWebPullScrollTest() {
        Espresso.onView(withId(R.id.rv_repository)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(4, click()))

        Espresso.onView(withId(R.id.rv_pull_request)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))
    }

    @Test
    fun pressBackPullRequestTest() {
        Espresso.onView(withId(R.id.rv_repository)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
        val mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        mDevice.pressBack()
    }

    @Test
    fun checkToolbarRepository(){
        Espresso.onView(withId(R.id.rv_repository)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
        val mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        mDevice.pressBack()
        Espresso.onView(withId(R.id.toolbar_main)).check(matches(isDisplayed()))
    }
}