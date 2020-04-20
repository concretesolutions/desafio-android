package com.marcos.desafioandroidconcrete

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.util.HumanReadables
import androidx.test.espresso.util.TreeIterables
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.marcos.desafioandroidconcrete.view.MainActivity
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException


/**
 * Created by marco on 19,Abril,2020
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    lateinit var nameRepository: String;

    @get:Rule
    val rule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun shouldShowAListOfPullRequestOfAnRepository_WhenClickAListItemRepository() {
        onView(isRoot()).perform(waitId(R.id.recyclerview, TimeUnit.SECONDS.toMillis(5)));
        try {
            Thread.sleep(5000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        onView(withId(R.id.recyclerview)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, clickOnViewChild(R.id.ll_list_item)))

        onView(isRoot()).perform(waitId(R.id.recyclerview, TimeUnit.SECONDS.toMillis(5)));
    }

    @Test
    fun shouldShowANameOfRepositoryInTabPullRequest_WhenClickAListItemRepository() {
        onView(isRoot()).perform(waitId(R.id.recyclerview, TimeUnit.SECONDS.toMillis(5)));

        try {
            Thread.sleep(5000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        onView(isRoot()).perform(getNameRepository(R.id.tv_name_repository));

        onView(withId(R.id.recyclerview)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, clickOnViewChild(R.id.ll_list_item)))

        onView(withText(nameRepository)).check(matches(isDisplayed()))
    }


    /** Perform action of waiting for a specific view id.  */
    fun getNameRepository(viewId: Int): ViewAction? {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isRoot()
            }

            override fun getDescription(): String {
                return ""
            }

            override fun perform(uiController: UiController, view: View) {
                val v: TextView = view.findViewById(viewId)
                nameRepository = v.text.toString()
            }
        }
    }


    fun clickOnViewChild(viewId: Int) = object : ViewAction {
        override fun getConstraints() = null

        override fun getDescription() = "Click on a child view with specified id."

        override fun perform(uiController: UiController, view: View) = click().perform(uiController, view.findViewById<View>(viewId))
    }

    /** Perform action of waiting for a specific view id.  */
    fun waitId(viewId: Int, millis: Long): ViewAction? {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isRoot()
            }

            override fun getDescription(): String {
                return "wait for a specific view with id <$viewId> during $millis millis."
            }

            override fun perform(uiController: UiController, view: View) {
                uiController.loopMainThreadUntilIdle()
                val startTime = System.currentTimeMillis()
                val endTime = startTime + millis
                val viewMatcher: Matcher<View> = withId(viewId)
                do {
                    for (child in TreeIterables.breadthFirstViewTraversal(view)) { // found view with required ID
                        if (viewMatcher.matches(child)) {
                            return
                        }
                    }
                    uiController.loopMainThreadForAtLeast(50)
                } while (System.currentTimeMillis() < endTime)
                throw PerformException.Builder()
                        .withActionDescription(this.description)
                        .withViewDescription(HumanReadables.describe(view))
                        .withCause(TimeoutException())
                        .build()
            }
        }
    }

}