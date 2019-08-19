package dev.theuzfaleiro.trendingongithub.ui.feature.pullrequest.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dev.theuzfaleiro.trendingongithub.ui.feature.pullrequest.model.data.PullRequest
import dev.theuzfaleiro.trendingongithub.ui.feature.pullrequest.model.data.User
import dev.theuzfaleiro.trendingongithub.ui.feature.pullrequest.repository.PullRequestRepository
import io.github.plastix.rxschedulerrule.RxSchedulerRule
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

private const val ERROR = 3

class PullRequestViewModelTest {

    @get:Rule
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val rxSchedulerRule = RxSchedulerRule()

    private val pullRequestRepository = mockk<PullRequestRepository>()

    lateinit var pullRequestViewModel: PullRequestViewModel

    @Before
    fun setUp() {
        pullRequestViewModel = PullRequestViewModel(pullRequestRepository)
    }

    @Test
    fun shouldDisplayOpenPullRequests_WhenFetchOpenPullRequests() {
        every {
            pullRequestRepository.fetchPullRequests("theuzfaleiro", "minimal-weather")
        } returns Single.just(
            listOf(
                PullRequest(
                    "https://bit.ly",
                    42,
                    "First Pull Request Title",
                    "First Pull Request Description",
                    User("theuzfaleiro", "https://bit.ly")
                )
            )
        )

        pullRequestViewModel.fetchPullRequests("theuzfaleiro", "minimal-weather")

        assertEquals(
            "First Pull Request Title",
            pullRequestViewModel.getRepositories().value!!.first().title
        )
    }

    @Test
    fun shouldDisplayAnError_WhenNoRepositoriesWereFetched() {
        every {
            pullRequestRepository.fetchPullRequests("theuzfaleiro", "minimal-weather")
        } returns Single.error(Throwable())

        pullRequestViewModel.fetchPullRequests("theuzfaleiro", "minimal-weather")

        assertNull(pullRequestViewModel.getRepositories().value)
        assertEquals(pullRequestViewModel.getLoading().value, ERROR)
    }
}