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
import com.haldny.githubapp.domain.model.ResponsePullRequest
import com.haldny.githubapp.domain.repository.IGithubRepository
import com.haldny.githubapp.domain.repository.IPullRequestRepository
import com.haldny.githubapp.matcher.RecyclerViewItemsCountMatcher.Companion.recyclerViewHasItemCount
import com.haldny.githubapp.presentation.main.MainActivity
import com.haldny.githubapp.presentation.pull.PullRequestActivity
import com.haldny.githubapp.recyclerview.RecyclerViewInteraction
import io.mockk.coEvery
import io.mockk.mockk
import org.hamcrest.core.IsNot.not
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
class PullRequestActivityTest: KoinTest {

    private val pullRequestRepository = mockk<IPullRequestRepository>()

    @get:Rule
    val activityRule: ActivityTestRule<PullRequestActivity> = ActivityTestRule(PullRequestActivity::class.java,
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
        coEvery { pullRequestRepository.getPullRequests("owner", "repository") } returns listOf()

        val intent = Intent()
        intent.putExtra("owner", "owner")
        intent.putExtra("repository", "repository")
        activityRule.launchActivity(intent)

        Espresso.onView(ViewMatchers.withId(R.id.pullRequestRoot))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.pullRequestLoading))
            .check(ViewAssertions.matches(not(ViewMatchers.isDisplayed())))
        Espresso.onView(ViewMatchers.withId(R.id.pullRequestRecyclerView))
            .check(ViewAssertions.matches(not(ViewMatchers.isDisplayed())))
    }

    @Test
    fun test_Loading_with_PullReuestsInList() {
        val pullRequests = getPullRequests(10);
        coEvery { pullRequestRepository.getPullRequests("owner", "repository") } returns pullRequests

        val intent = Intent()
        intent.putExtra("owner", "owner")
        intent.putExtra("repository", "repository")
        activityRule.launchActivity(intent)

        Espresso.onView(ViewMatchers.withId(R.id.pullRequestRoot))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.pullRequestRecyclerView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        RecyclerViewInteraction.onRecyclerView<ResponsePullRequest>(withId(R.id.pullRequestRecyclerView))
            .withItems(pullRequests)
            .check { (id, name), view, exception ->
                matches(hasDescendant(withText(name))).check(
                    view,
                    exception
                )
            }
    }

    @Test
    fun test_Check_ItensOnRecycler() {
        val pullRequests = getPullRequests(10);
        coEvery { pullRequestRepository.getPullRequests("owner", "repository") } returns pullRequests

        val intent = Intent()
        intent.putExtra("owner", "owner")
        intent.putExtra("repository", "repository")
        activityRule.launchActivity(intent)

        Espresso.onView(ViewMatchers.withId(R.id.pullRequestRoot))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.pullRequestRecyclerView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(withId(R.id.pullRequestRecyclerView)).check(
            matches(recyclerViewHasItemCount(10))
        )
    }

    private fun getPullRequests(numberOFPullRequests: Int): List<ResponsePullRequest> {
        val pullRequests = IntRange(0, numberOFPullRequests - 1).map {
            val title = "Title - $it"
            val ownerName = "Owner - $it"
            val ownerPhoto = "https://i.annihil.us/u/prod/marvel/i/mg/c/60/55b6a28ef24fa.jpg"
            val body = "Description pull request - $it"
            val owner = Owner(it, ownerName, ownerPhoto)
            val pullRequest = ResponsePullRequest(it, title, body, "open", "https://www.github.com/haldny",
                "2019-11-26T18:46:19Z", owner)
            pullRequest
        }

        return pullRequests
    }

    val localRepository = module {
        single<IPullRequestRepository> { pullRequestRepository }
    }

    val allModule = listOf(viewModelModule, localRepository)
}