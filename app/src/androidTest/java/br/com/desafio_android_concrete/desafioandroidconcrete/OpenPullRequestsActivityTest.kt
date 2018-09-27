package br.com.desafio_android_concrete.desafioandroidconcrete

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import br.com.desafio.concrete.R
import br.com.desafio.concrete.view.pullrequest.PullRequestsActivity
import br.com.desafio.concrete.view.repository.RepositoryListActivity
import br.com.desafio.concrete.view.repository.adapter.RepoViewHolder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class OpenPullRequestsActivityTest : BaseTest() {

    @get:Rule
    var activityTestRule = object : ActivityTestRule<RepositoryListActivity>(RepositoryListActivity::class.java){}

    @Test
    fun openPullRequestsActivity( ){

        //Check is loading is visible
        onView(withId(R.id.swipeRefreshLayout)).check(ViewAssertions.matches(isDisplayed()))

        delay() //Delay in order to wait the response from API

        Intents.init()
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition<RepoViewHolder>(5, ViewActions.click()))

        delay()

        intended(hasComponent(PullRequestsActivity::class.java.name))
        Intents.release()

    }

}
