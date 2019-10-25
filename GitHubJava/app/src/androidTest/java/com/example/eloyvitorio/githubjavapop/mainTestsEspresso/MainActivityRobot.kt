package com.example.eloyvitorio.githubjavapop.mainTestsEspresso

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra
import android.support.test.espresso.matcher.ViewMatchers
import com.example.eloyvitorio.githubjavapop.BaseTestRobot
import com.example.eloyvitorio.githubjavapop.ui.repositories.RepositoriesAdapter

fun mainRepositoryItem(func: MainActivityRobot.() -> Unit) = MainActivityRobot().apply { func() }

class MainActivityRobot : BaseTestRobot() {
    fun repositoryTitleIsDisplayed(title: String) = matchTextViewByTextIsDisplayed(title)

    fun repositoryDescripitonIsDisplayed(description: String) = matchTextViewByTextIsDisplayed(
            description)

    fun repositoryForkNumberIsDisplayed(forks: String) = matchTextViewByTextIsDisplayed(forks)

    fun repositoryStargazersIsDisplayed(stargazers: String) = matchTextViewByTextIsDisplayed(
            stargazers)

    fun repositoryUserLoginIsDisplayed(login: String) = matchTextViewByTextIsDisplayed(login)

    fun repositoryUserNameIsDisplayed(name: String) = matchTextViewByTextIsDisplayed(name)

    fun forkIconIsDisplayed(recyclerId: Int, position: Int, imageViewId: Int) =
            matchImageViewIntoRecyclerViewIsDisplayed(recyclerId, position, imageViewId)

    fun loadErrorImageIsDisplayed(resId: Int) = matchViewByIdIsDisplayed(resId)

    fun loadErrorMessageIsDisplayed(text: String) = matchTextViewByTextIsDisplayed(text)

    fun loadErrorRetryButtonIsClickable(resId: Int) = matchButtonByIdIsClickable(resId)

    fun starIconIsDisplayed(recyclerId: Int, position: Int, imageViewId: Int) =
            matchImageViewIntoRecyclerViewIsDisplayed(recyclerId, position, imageViewId)

    fun userPhotoIsDisplayed(recyclerId: Int, position: Int, imageViewId: Int) =
            matchImageViewIntoRecyclerViewIsDisplayed(recyclerId, position, imageViewId)

    fun scrollToPositionInList(recyclerId: Int, position: Int): ViewInteraction = onView(ViewMatchers.withId(recyclerId))
            .perform(RecyclerViewActions.scrollToPosition<RepositoriesAdapter.ViewHolder>(position))

    fun clickOnPositionInList(recyclerId: Int, position: Int): ViewInteraction = onView(ViewMatchers.withId(recyclerId))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RepositoriesAdapter.ViewHolder>(
                    position, ViewActions.click()))

    fun intendedPullRequestList(
            className: String,
            extraKeyRepositoryName: String,
            extraValueRepositoryName: String,
            extraKeyLogin: String,
            extraValueLogin: String) {
        intended(hasComponent(className))
        intended(hasExtra(extraKeyRepositoryName, extraValueRepositoryName))
        intended(hasExtra(extraKeyLogin, extraValueLogin))
    }
}