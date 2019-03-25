package dev.renatoneto.githubrepos.ui

import dev.renatoneto.githubrepos.R
import dev.renatoneto.githubrepos.base.BaseUnitTest
import dev.renatoneto.githubrepos.network.github.GithubTestService
import dev.renatoneto.githubrepos.ui.repositorydetails.RepositoryDetailsViewModel
import io.reactivex.Observable
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class RepositoryDetailsViewModelUnitTest : BaseUnitTest() {

    private fun viewModel(): RepositoryDetailsViewModel {
        return RepositoryDetailsViewModel(dataSource, GithubTestService.listResponse.items.first(), scheduler)
    }

    @Test
    fun testBeforeRequest() {
        val viewModel = viewModel()

        assertFalse(viewModel.contentLoaded)
    }

    @Test
    fun testLoadPullRequestsSuccesss() {
        val viewModel = viewModel()

        `when`(dataSource.getPullRequestsList("login", "repository 1"))
            .thenReturn(Observable.just(GithubTestService.pullRequestsResponse))

        viewModel.loadPullRequests()
        assertTrue(viewModel.contentLoaded)
        assertEquals(viewModel.pullRequestsList, GithubTestService.pullRequestsResponse)
    }

    @Test
    fun testLoadPullRequestsEmptyResponse() {
        val viewModel = viewModel()

        `when`(dataSource.getPullRequestsList("login", "repository 1"))
            .thenReturn(Observable.just(GithubTestService.pullRequestsEmptyResponse))

        viewModel.loadPullRequests()
        assertEquals(viewModel.pullRequestsList, GithubTestService.pullRequestsEmptyResponse)
        assertEquals(viewModel.error.value, R.string.error_no_pull_requests)
    }

    @Test
    fun testUnexpectedError() {
        val exception = mock(Exception::class.java)
        val viewModel = viewModel()

        `when`(dataSource.getPullRequestsList("login", "repository 1")).thenReturn(Observable.error(exception))

        viewModel.loadPullRequests()
        assertEquals(viewModel.error.value, R.string.error_unexpected)
    }

}
