package br.com.desafio_android_concrete.desafioandroidconcrete

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import br.com.desafio.concrete.R
import br.com.desafio.concrete.view.repository.RepositoryListActivity
import br.com.desafio.concrete.view.repository.adapter.RepoViewHolder
import kotlinx.android.synthetic.main.repository_list_activity.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ScrollToEnd : BaseTest() {


    @get:Rule
    var activityTestRule = object : ActivityTestRule<RepositoryListActivity>(RepositoryListActivity::class.java){}

    @Test
    fun scrollToEnd( ){

        delay() //Delay in order to wait the response from API

        val count = activityTestRule.activity.recyclerView.adapter.itemCount
        val lastPosition = count - 1
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.scrollToPosition<RepoViewHolder>(lastPosition))

    }
}
