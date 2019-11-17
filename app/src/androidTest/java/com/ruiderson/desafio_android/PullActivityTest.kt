package com.ruiderson.desafio_android

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.ruiderson.desafio_android.enum.IntentCode
import com.ruiderson.desafio_android.models.Repository
import com.ruiderson.desafio_android.models.RepositoryOwner
import com.ruiderson.desafio_android.ui.RepositoryAdapter
import org.hamcrest.Matchers.not
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class PullActivityTest{

    @Rule
    @JvmField
    val activityRule: ActivityTestRule<PullActivity> =
        object : ActivityTestRule<PullActivity>(PullActivity::class.java) {
            override fun getActivityIntent(): Intent {
                val item = Repository(507775, "elasticsearch", "Open Source, Distributed, RESTful Search Engine", 15325, 45416, RepositoryOwner("https://avatars0.githubusercontent.com/u/6764390?v=4", "elastic"))
                val intent = Intent()
                intent.putExtra(IntentCode.REPOSITORY_EXTRA.value, item)
                return intent
            }
        }


    @Test
    fun activityTest() {
        onView(withId(R.id.fab))
            .check(matches(not(isDisplayed())))
        Thread.sleep(2000)


        onView(withId(R.id.pullRecyclerView))
            .perform(swipeDown())
        Thread.sleep(1000)
        val itemCount = getListCount()
        Assert.assertTrue(itemCount > 0)


        onView(withId(R.id.pullRecyclerView))
            .perform(swipeUp())
        onView(withId(R.id.fab))
            .check(matches(isDisplayed()))
            .perform(click())



        Thread.sleep(1000)
        onView(withId(R.id.pullRecyclerView))
            .perform(swipeUp())
        onView(withId(R.id.pullRecyclerView))
            .perform(swipeUp())


        Thread.sleep(1000)
        onView(withId(R.id.pullRecyclerView))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RepositoryAdapter.MyViewHolder>(0, click()))
    }



    private fun getListCount(): Int{
        var count = 0
        val pullRecyclerView: RecyclerView = activityRule.activity.findViewById(R.id.pullRecyclerView)
        pullRecyclerView.adapter?.let {
            val listCount = pullRecyclerView.adapter?.getItemCount()
            listCount?.let {
                count = listCount
            }
        }

        return count
    }


}