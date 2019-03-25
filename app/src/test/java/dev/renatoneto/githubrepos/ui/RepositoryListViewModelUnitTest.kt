package dev.renatoneto.githubrepos.ui

import dev.renatoneto.githubrepos.R
import dev.renatoneto.githubrepos.base.BaseUnitTest
import dev.renatoneto.githubrepos.network.github.GithubTestService
import dev.renatoneto.githubrepos.ui.repositorylist.RepositoryListViewModel
import io.reactivex.Observable
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.*
import java.io.IOException

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class RepositoryListViewModelUnitTest : BaseUnitTest() {

    private fun viewModel(): RepositoryListViewModel {
        return RepositoryListViewModel(dataSource, scheduler)
    }

    @Test
    fun testeBeforeRequest() {
        val viewModel = viewModel()

        assertEquals(viewModel.currentPage, 1)
        assertEquals(viewModel.lastPageLoaded, 0)
    }

    @Test
    fun testLastPageLoadedAfterSamePageRequest() {
        val viewModel = viewModel()

        viewModel.lastPageLoaded = 1
        viewModel.loadRepositories()

        assertEquals(viewModel.lastPageLoaded, 1)
    }

    @Test
    fun testGetRepositoriesSuccesss() {
        val viewModel = viewModel()

        `when`(dataSource.getRepositoriesList(1)).thenReturn(Observable.just(GithubTestService.listResponse))

        viewModel.loadRepositories()
        assertEquals(viewModel.lastPageLoaded, 1)
        assertEquals(viewModel.repositoriesList, GithubTestService.listResponse.items)
    }

    @Test
    fun testGetRepositoriesEmptyResponse() {
        val viewModel = viewModel()

        `when`(dataSource.getRepositoriesList(1)).thenReturn(Observable.just(GithubTestService.listEmptyResponse))

        viewModel.loadRepositories()
        assertEquals(viewModel.repositoriesList, GithubTestService.listEmptyResponse.items)
    }

    @Test
    fun testNetworkError() {
        val exception = mock(IOException::class.java)
        val viewModel = viewModel()

        `when`(dataSource.getRepositoriesList(1)).thenReturn(Observable.error(exception))

        viewModel.loadRepositories()
        assertEquals(viewModel.error.value, R.string.error_connection)
    }

    @Test
    fun testUnexpectedError() {
        val exception = mock(Exception::class.java)
        val viewModel = viewModel()

        `when`(dataSource.getRepositoriesList(1)).thenReturn(Observable.error(exception))

        viewModel.loadRepositories()
        assertEquals(viewModel.error.value, R.string.error_unexpected)
    }

}
