package com.example.eloyvitorio.githubjavapop.pullrequestTestsEspresso

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.BundleMatchers.hasEntry
import android.support.test.espresso.intent.matcher.IntentMatchers.hasExtras
import android.support.test.espresso.intent.matcher.IntentMatchers.hasAction
import android.support.test.espresso.intent.matcher.IntentMatchers.hasData
import android.support.test.espresso.matcher.ViewMatchers
import com.example.eloyvitorio.githubjavapop.BaseTestRobot
import com.example.eloyvitorio.githubjavapop.ui.pullrequest.PullRequestsAdapter
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.core.AllOf.allOf

fun pullRequestListItem(func: PullRequestsListRobot.() -> Unit) = PullRequestsListRobot().apply { func() }

class PullRequestsListRobot : BaseTestRobot() {
    fun repositoryTitleIsDisplayed(title: String) = matchTextViewByTextIsDisplayed(title)

    fun repositoryBodyIsDisplayed(body: String) = matchTextViewByTextIsDisplayed(body)

    fun repositoryUserNameIsDisplayed(name: String) = matchTextViewByTextIsDisplayed(name)

    fun repositoryTrueNameIsDisplayed(trueName: String) = matchTextViewByTextIsDisplayed(
            trueName)

    fun userPhotoIsDisplayed(recyclerId: Int, position: Int, imageViewId: Int) =
            matchImageViewIntoRecyclerViewIsDisplayed(recyclerId, position, imageViewId)

    fun imageErrorLoadIsDisplayed(resId: Int) = matchViewByIdIsDisplayed(resId)

    fun messageErrorLoadIsDisplayed(text: String) = matchTextViewByTextIsDisplayed(text)

    fun buttonErrorLoadIsClickable(resId: Int) = matchButtonByIdIsClickable(resId)

    fun imageEmptyListIsDisplayed(resId: Int) = matchViewByIdIsDisplayed(resId)

    fun messageEmptyListIsDisplayed(text: String) = matchTextViewByTextIsDisplayed(text)

    fun clickOnPositionInList(recyclerId: Int, position: Int): ViewInteraction = onView(
            ViewMatchers.withId(recyclerId))
            .perform(RecyclerViewActions.actionOnItemAtPosition<PullRequestsAdapter.ViewHolder>(
                    position, ViewActions.click()))

    fun intendedPullRequestPage(
            action: String,
            extraUri: String,
            extraTitle: String,
            actionChooser: String) {
        intended(allOf(
                hasExtras(allOf(
                        hasEntry(equalTo(Intent.EXTRA_INTENT), hasAction(action)),
                        hasEntry(equalTo(Intent.EXTRA_INTENT), hasData(extraUri)),
                        hasEntry(equalTo(Intent.EXTRA_TITLE), containsString(extraTitle))
                )),
                hasAction(equalTo(actionChooser))
        ))
    }
}