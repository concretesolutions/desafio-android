package com.pedrenrique.githubapp.features.pr

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.pedrenrique.githubapp.core.data.PaginatedData
import com.pedrenrique.githubapp.core.data.PullRequest
import com.pedrenrique.githubapp.core.data.Repository
import com.pedrenrique.githubapp.core.data.User
import com.pedrenrique.githubapp.core.domain.ListPRFromRepository
import com.pedrenrique.githubapp.core.domain.LoadMorePRFromRepository
import com.pedrenrique.githubapp.core.exceptions.EmptyResultException
import com.pedrenrique.githubapp.core.exceptions.NoMoreResultException
import com.pedrenrique.githubapp.features.common.DataState
import com.pedrenrique.githubapp.features.common.adapter.model.PullRequestModelHolder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class PullRequestsViewModelTest {

    @Rule
    @JvmField
    var rule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    private lateinit var viewModel: PullRequestsViewModel

    @Mock
    private lateinit var listPRFromRepository: ListPRFromRepository
    @Mock
    private lateinit var loadMorePRFromRepository: LoadMorePRFromRepository
    @Mock
    private lateinit var observer: Observer<DataState>

    @Before
    fun setUp() {
        viewModel = PullRequestsViewModel(listPRFromRepository, loadMorePRFromRepository)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `should list pull requests once`() {
        runBlocking {
            val repository = Repository(
                "repository", "user/repository",
                "description", 1, 1, User("", "")
            )
            val pullRequest = PullRequestModelHolder(
                PullRequest(1, "title", "body", User("", ""), "url", Date())
            )
            val params = ListPRFromRepository.Params(repository)
            `when`(listPRFromRepository.invoke(params))
                .thenReturn(PaginatedData(1, listOf(pullRequest.pr)))

            viewModel.state.observeForever(observer)
            assertEquals(viewModel.page, 0)
            viewModel.load(repository)
            assertEquals(viewModel.page, 1)

            verify(observer).onChanged(DataState.Pending)
            verify(observer).onChanged(DataState.Loaded(listOf(pullRequest)))

            verify(listPRFromRepository).invoke(params)

            viewModel.load(repository)

            verifyNoMoreInteractions(observer)
            verifyNoMoreInteractions(listPRFromRepository)
            verifyZeroInteractions(loadMorePRFromRepository)
        }
    }

    @Test
    fun `should load more pull requests`() {
        runBlocking {
            val repository = Repository(
                "repository", "user/repository",
                "description", 1, 1, User("", "")
            )

            val pr1 = PullRequestModelHolder(
                PullRequest(1, "title1", "body1", User("user1", "login1"), "url1", Date())
            )
            val pr2 = PullRequestModelHolder(
                PullRequest(2, "title2", "body2", User("user2", "login2"), "url2", Date())
            )

            val listParams = ListPRFromRepository.Params(repository)
            `when`(listPRFromRepository.invoke(listParams))
                .thenReturn(PaginatedData(1, listOf(pr1.pr)))

            val nextParams = LoadMorePRFromRepository.Params(1, repository)
            `when`(loadMorePRFromRepository.invoke(nextParams))
                .thenReturn(PaginatedData(2, listOf(pr2.pr)))

            viewModel.state.observeForever(observer)
            viewModel.load(repository)

            verify(observer, times(2)).onChanged(any(DataState::class.java))
            verify(listPRFromRepository).invoke(listParams)
            assertEquals(viewModel.page, 1)

            viewModel.loadMore(repository)

            assertEquals(viewModel.page, 2)
            verify(observer).onChanged(DataState.NextPending(listOf(pr1)))
            verify(observer).onChanged(DataState.Loaded(listOf(pr1, pr2)))
            verify(loadMorePRFromRepository).invoke(nextParams)

            verifyNoMoreInteractions(observer)
            verifyNoMoreInteractions(listPRFromRepository)
            verifyNoMoreInteractions(loadMorePRFromRepository)
        }
    }

    @Test
    fun `should fail when try to load pull requests`() {
        runBlocking {
            val repository = Repository(
                "repository", "user/repository",
                "description", 1, 1, User("", "")
            )
            val exception = Exception("error!")
            val params = ListPRFromRepository.Params(repository)
            `when`(listPRFromRepository.invoke(params))
                .thenThrow(exception)

            viewModel.state.observeForever(observer)
            viewModel.load(repository)

            verify(observer).onChanged(DataState.Pending)
            verify(observer).onChanged(DataState.Failed(exception))
            verify(listPRFromRepository).invoke(params)
            assertEquals(viewModel.page, 0)

            verifyNoMoreInteractions(observer)
            verifyNoMoreInteractions(listPRFromRepository)
            verifyNoMoreInteractions(loadMorePRFromRepository)
        }
    }

    @Test
    fun `should fail when try to load more pull requests`() {
        runBlocking {
            val repository = Repository(
                "repository", "user/repository",
                "description", 1, 1, User("", "")
            )
            val pullRequest = PullRequestModelHolder(
                PullRequest(1, "title", "body", User("", ""), "url", Date())
            )

            val listParams = ListPRFromRepository.Params(repository)
            `when`(listPRFromRepository.invoke(listParams))
                .thenReturn(PaginatedData(1, listOf(pullRequest.pr)))

            val exception = Exception("error!")
            val nextParams = LoadMorePRFromRepository.Params(1, repository)
            `when`(loadMorePRFromRepository.invoke(nextParams))
                .thenThrow(exception)

            viewModel.state.observeForever(observer)
            viewModel.load(repository)

            verify(observer, times(2)).onChanged(any(DataState::class.java))
            verify(listPRFromRepository).invoke(listParams)

            viewModel.loadMore(repository)

            verify(observer).onChanged(any(DataState.NextPending::class.java))
            verify(observer).onChanged(DataState.NextFailed(exception, listOf(pullRequest)))
            verify(loadMorePRFromRepository)
                .invoke(nextParams)
            assertEquals(viewModel.page, 1)

            verifyNoMoreInteractions(observer)
            verifyNoMoreInteractions(listPRFromRepository)
            verifyNoMoreInteractions(loadMorePRFromRepository)
        }
    }

    @Test
    fun `should return empty pull requests`() {
        runBlocking {
            val repository = Repository(
                "repository", "user/repository",
                "description", 1, 1, User("", "")
            )

            val exception = EmptyResultException()
            val params = ListPRFromRepository.Params(repository)
            `when`(listPRFromRepository.invoke(params))
                .thenThrow(exception)

            viewModel.state.observeForever(observer)
            viewModel.load(repository)

            assertEquals(viewModel.page, 0)
            verify(observer).onChanged(DataState.Pending)
            verify(observer).onChanged(DataState.Empty)
            verify(listPRFromRepository).invoke(params)

            verifyNoMoreInteractions(observer)
            verifyNoMoreInteractions(listPRFromRepository)
            verifyZeroInteractions(loadMorePRFromRepository)
        }
    }

    @Test
    fun `should reach the end of pull requests list`() {
        runBlocking {
            val repository = Repository(
                "repository", "user/repository",
                "description", 1, 1, User("", "")
            )

            val pr1 = PullRequestModelHolder(
                PullRequest(1, "title1", "body1", User("user1", "login1"), "url1", Date())
            )
            val pr2 = PullRequestModelHolder(
                PullRequest(2, "title2", "body2", User("user2", "login2"), "url2", Date())
            )

            val listParams = ListPRFromRepository.Params(repository)
            `when`(listPRFromRepository.invoke(listParams))
                .thenReturn(PaginatedData(1, listOf(pr1.pr)))

            val nextParams = LoadMorePRFromRepository.Params(1, repository)
            `when`(loadMorePRFromRepository.invoke(nextParams))
                .thenReturn(PaginatedData(2, listOf(pr2.pr)))

            val exception = NoMoreResultException()
            `when`(loadMorePRFromRepository.invoke(nextParams.copy(2)))
                .thenThrow(exception)

            viewModel.state.observeForever(observer)
            viewModel.load(repository)
            viewModel.loadMore(repository)

            verify(observer).onChanged(DataState.Pending)
            verify(observer).onChanged(any(DataState.NextPending::class.java))
            verify(observer, times(2))
                .onChanged(any(DataState.Loaded::class.java))
            verifyNoMoreInteractions(observer)

            verify(listPRFromRepository).invoke(listParams)
            verify(loadMorePRFromRepository).invoke(nextParams)

            viewModel.loadMore(repository)

            verify(observer, times(2))
                .onChanged(any(DataState.NextPending::class.java))
            verify(observer)
                .onChanged(DataState.Completed(listOf(pr1, pr2)))
            verify(loadMorePRFromRepository).invoke(nextParams.copy(2))
            assertEquals(viewModel.page, 2)

            verifyNoMoreInteractions(observer)
            verifyNoMoreInteractions(listPRFromRepository)
            verifyNoMoreInteractions(loadMorePRFromRepository)
        }
    }
}