package com.jsouza.repopullrequests.presentation

import com.jsouza.repopullrequests.domain.model.PullRequests
import com.jsouza.repopullrequests.domain.usecase.FetchPullRequestsFromApi
import com.jsouza.repopullrequests.domain.usecase.GetPullRequestsFromDatabase
import com.jsouza.repopullrequests.presentation.utils.CoroutineTestRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.just
import io.mockk.mockk
import io.mockk.mockkClass
import io.mockk.runs
import io.mockk.spyk
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PullRequestsViewModelTest {

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    private val pullRequests = mockkClass(PullRequests::class)
    private val fetchPullRequestsFromApi: FetchPullRequestsFromApi = mockk()
    private val getPullRequestsFromDatabase: GetPullRequestsFromDatabase = mockk()

    private val pullRequestsViewModel = spyk(
        PullRequestsViewModel(
            coroutineContext = coroutinesTestRule.testDispatcher,
            fetchPullRequestsFromApi = fetchPullRequestsFromApi,
            getPullRequestsFromDatabase = getPullRequestsFromDatabase
        )
    )

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `GIVEN the application wants to get pullRequests from database WHEN a repositoryId was given THEN it should load the data in livedata as domain model`() {
        coEvery { pullRequestsViewModel.getPullRequestsFromDatabase.invoke(any()).observeForever { pullRequestsFromDatabase ->
            Assert.assertEquals(pullRequestsFromDatabase, pullRequests) }
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `GIVEN the activity wants to start load pullRequests on viewModel WHEN a repositoryId, userName and repositoryName was given THEN it should load`() {

        coEvery { pullRequestsViewModel.fetchPullRequestsFromApi(any(), any(), any()) } just runs

        coroutinesTestRule.testDispatcher.runBlockingTest {
            pullRequestsViewModel.loadPullRequestsOfRepository(
                userName,
                repositoryName,
                repositoryId
            )
        }
    }

    @Test
    fun `GIVEN the activity wants to start to get pullRequests from viewModel WHEN a repositoryId was given THEN it should return the data`() {
        coEvery { pullRequestsViewModel.returnPullRequestsOnLiveData(any()).observeForever { pullRequestsFromDatabase ->
            Assert.assertEquals(pullRequestsFromDatabase, pullRequests)
        } }
    }

    private companion object {
        const val repositoryId = 0L
        const val repositoryName = ""
        const val userName = ""
    }
}
