package com.example.eloyvitorio.githubjavapop.pullrequestTestsUnit

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.eloyvitorio.githubjavapop.data.model.HeadPullRequest
import com.example.eloyvitorio.githubjavapop.data.model.PullRequest
import com.example.eloyvitorio.githubjavapop.data.model.RepoPullRequest
import com.example.eloyvitorio.githubjavapop.data.model.UserPullRequest
import com.example.eloyvitorio.githubjavapop.data.network.CallBackPullRequest
import com.example.eloyvitorio.githubjavapop.data.network.PullRequestsAPI
import com.example.eloyvitorio.githubjavapop.di.appModule
import com.example.eloyvitorio.githubjavapop.ui.pullrequest.PullRequestsViewModel
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.capture
import com.nhaarman.mockitokotlin2.verify
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.get
import org.koin.test.inject
import org.koin.test.mock.declareMock
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PullRequestListUnitTest : KoinTest {
    private val viewModel: PullRequestsViewModel by inject()

    @get:Rule
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Captor
    private lateinit var callBackPullRequest: ArgumentCaptor<CallBackPullRequest>

    @Before
    fun setUp() {
        startKoin {
            modules(appModule)
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun whenFetchPullRequests_shouldPostPullRequestList() {
        val list = getMockPullRequestList()
        declareMock<PullRequestsAPI>()
        val pullRequestsAPIApiMock = get<PullRequestsAPI>()

        viewModel.fetchPullRequests("CyC2018", "CS-Notes")

        callBackPullRequest.apply {
            verify(pullRequestsAPIApiMock).listPullRequest(any(), any(), capture(this))
            this.value.onSucessGetPullRequest(list)
            assertEquals(viewModel.pullRequestList.value, list)
        }
    }

    @Test
    fun whenFetchPullRequestsWithSuccess_shouldNoPostErrorMessage() {
        val list = getMockPullRequestList()
        declareMock<PullRequestsAPI>()
        val pullRequestsAPIApiMock = get<PullRequestsAPI>()

        viewModel.fetchPullRequests("CyC2018", "CS-Notes")

        callBackPullRequest.apply {
            verify(pullRequestsAPIApiMock).listPullRequest(any(), any(), capture(this))
            this.value.onSucessGetPullRequest(list)
            assertEquals(viewModel.error.value, null)
        }
    }

    @Test
    fun whenFetchPullRequestsWithError_shouldPostErrorMessage() {
        val mockMessage = "Um erro ocorreu. \nTente carregar novamente."
        declareMock<PullRequestsAPI>()
        val pullRequestsAPIApiMock = get<PullRequestsAPI>()

        viewModel.fetchPullRequests("CyC2018", "CS-Notes")

        callBackPullRequest.apply {
            verify(pullRequestsAPIApiMock).listPullRequest(any(), any(), capture(this))
            this.value.onErrorGetPullRequest(mockMessage)

            assertEquals(viewModel.error.value, mockMessage)
        }
    }

    private fun getMockPullRequestList(): List<PullRequest> {
        val userPullRequest = UserPullRequest(login = "tylitianrui",
                id = 31768692,
                avatarUrl = "https://avatars0.githubusercontent.com/u/31768692?v=4")
        val repoPullRequest = RepoPullRequest(fullName = "pzawitowski/java")
        val headPullRequest = HeadPullRequest(repo = repoPullRequest)
        return arrayListOf(
                PullRequest(htmlUrl = "https://github.com/CyC2018/CS-Notes/pull/740",
                        title = "Gap lock",
                        user = userPullRequest,
                        body = "NEXT-KEY Lock + Record Lock",
                        head = headPullRequest)
        )
    }
}