package com.ruiderson.desafio_android

import android.view.Gravity
import androidx.recyclerview.widget.RecyclerView
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.contrib.DrawerMatchers.isClosed
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import com.ruiderson.desafio_android.ui.RepositoryAdapter
import org.hamcrest.Matchers.not
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest{

    @Rule
    @JvmField
    val activityRule: IntentsTestRule<MainActivity> = IntentsTestRule(MainActivity::class.java)


    @Test
    fun activityTest() {
        onView(withId(R.id.fab))
            .check(matches(not(isDisplayed())))
        Thread.sleep(5000)


        onView(withId(R.id.drawer_layout))
            .check(matches(isClosed(Gravity.LEFT)))
            .perform(DrawerActions.open())
        Thread.sleep(1000)
        onView(withId(R.id.drawer_layout))
            .perform(DrawerActions.close())


        Thread.sleep(500)
        onView(withId(R.id.repositoryRecyclerView))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RepositoryAdapter.MyViewHolder>(0, click()))
        intended(hasComponent( "com.ruiderson.desafio_android.PullActivity"))
        Thread.sleep(2000)
        onView(isRoot()).perform(pressBack())


        onView(withId(R.id.repositoryRecyclerView))
            .perform(swipeDown())
        Thread.sleep(1000)
        var itemCount = getListCount()
        Assert.assertTrue(itemCount > 0)



        onView(withId(R.id.repositoryRecyclerView))
            .perform(swipeUp())
        onView(withId(R.id.fab))
            .check(matches(isDisplayed()))
            .perform(click())



        Thread.sleep(1000)
        onView(withId(R.id.repositoryRecyclerView))
            .perform(swipeUp())
        onView(withId(R.id.repositoryRecyclerView))
            .perform(swipeUp())


        Thread.sleep(1000)
        itemCount = getListCount()
        onView(withId(R.id.repositoryRecyclerView))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RepositoryAdapter.MyViewHolder>(itemCount - 5, click()))
    }



    private fun getListCount(): Int{
        var count = 0
        val repositoryRecyclerView: RecyclerView = activityRule.activity.findViewById(R.id.repositoryRecyclerView)
        repositoryRecyclerView.adapter?.let {
            val listCount = repositoryRecyclerView.adapter?.getItemCount()
            listCount?.let {
                count = listCount
            }
        }

        return count
    }


}