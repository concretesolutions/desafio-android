package com.example.eloyvitorio.githubjavapop

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.espresso.matcher.ViewMatchers.isClickable
import android.support.test.espresso.matcher.ViewMatchers.hasDescendant
import org.hamcrest.Matchers.allOf

open class BaseTestRobot {

    fun matchViewByIdIsDisplayed(resId: Int): ViewInteraction = onView(withId(resId))
            .check(ViewAssertions.matches(isDisplayed()))

    fun matchTextViewByTextIsDisplayed(text: String): ViewInteraction =
            onView(withText(text)).check(ViewAssertions.matches(isDisplayed()))

    fun matchButtonByIdIsClickable(resId: Int): ViewInteraction = onView(withId(resId))
            .check(ViewAssertions.matches(isClickable()))

    fun matchImageViewIntoRecyclerViewIsDisplayed(recyclerId: Int,
                                                  position: Int,
                                                  imageViewId: Int): ViewInteraction =
            onView(withId(recyclerId))
            .check(ViewAssertions.matches(Utils.withViewAtPosition(position,
                                    hasDescendant(allOf(withId(imageViewId),
                                            isDisplayed())))
            ))
}