package com.haldny.githubapp

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.haldny.githubapp.common.di.viewModelModule
import com.haldny.githubapp.domain.model.Owner
import com.haldny.githubapp.domain.model.Repository
import com.haldny.githubapp.domain.repository.IGithubRepository
import com.haldny.githubapp.domain.repository.IPullRequestRepository
import com.haldny.githubapp.matcher.RecyclerViewItemsCountMatcher.Companion.recyclerViewHasItemCount
import com.haldny.githubapp.presentation.main.MainActivity
import com.haldny.githubapp.recyclerview.RecyclerViewInteraction
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest: KoinTest {

    private val githubRepository = mockk<IGithubRepository>()
    private val pullRequestRepository = mockk<IPullRequestRepository>()

    @get:Rule
    val activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java,
        true, false)

    @Before
    fun setup() {
        stopKoin()
        startKoin {
            modules(allModule)
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun test_Loading_with_EmptyList() {
        coEvery { githubRepository.getRepositories(1) } returns listOf()

        activityRule.launchActivity(Intent())

        Espresso.onView(ViewMatchers.withId(R.id.mainRoot))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.mainLoading))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.mainRecyclerView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun test_Loading_with_RepositoriesInList() {
        val repositories = getRepositories(10);
        coEvery { githubRepository.getRepositories(1) } returns repositories

        activityRule.launchActivity(Intent())

        Espresso.onView(ViewMatchers.withId(R.id.mainRoot))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.mainRecyclerView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        RecyclerViewInteraction.onRecyclerView<Repository>(withId(R.id.mainRecyclerView))
            .withItems(repositories)
            .check { (id, name), view, exception ->
                matches(hasDescendant(withText(name))).check(
                    view,
                    exception
                )
            }
    }

    @Test
    fun test_Open_PullRequestActivity() {

        val repositories = getRepositories(10);
        coEvery { githubRepository.getRepositories(1) } returns repositories

        val repositoryToBeClickedPosition = 0

        activityRule.launchActivity(Intent())

        Espresso.onView(ViewMatchers.withId(R.id.mainRoot))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.mainRecyclerView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(withId(R.id.mainRecyclerView))
            .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(repositoryToBeClickedPosition, click()))
    }

    @Test
    fun test_Check_ItensOnRecycler() {
        val numberOfItems = 30
        val repositories = getRepositories(numberOfItems);
        coEvery { githubRepository.getRepositories(1) } returns repositories

        activityRule.launchActivity(Intent())

        Espresso.onView(ViewMatchers.withId(R.id.mainRoot))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.mainRecyclerView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(withId(R.id.mainRecyclerView)).check(
            matches(recyclerViewHasItemCount(numberOfItems + 1))
        )
    }

    private fun getRepositories(numberOfRepositories: Int): List<Repository> {
        val repositories = IntRange(0, numberOfRepositories - 1).map {
            val name = "Repository - $it"
            val ownerName = "Owner - $it"
            val ownerPhoto = "https://i.annihil.us/u/prod/marvel/i/mg/c/60/55b6a28ef24fa.jpg"
            val description = "Description repository - $it"
            val owner = Owner(it, ownerName, ownerPhoto)
            val repository = Repository(it, name, description, 30, 60,
                "2019-11-26T18:46:19Z", owner)
            repository
        }

        return repositories
    }

    val localRepository = module {
        single<IGithubRepository> { githubRepository }
        single<IPullRequestRepository> { pullRequestRepository }
    }

    val allModule = listOf(viewModelModule, localRepository)
}