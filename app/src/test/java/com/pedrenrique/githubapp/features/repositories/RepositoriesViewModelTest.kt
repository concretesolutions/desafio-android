package com.pedrenrique.githubapp.features.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.pedrenrique.githubapp.core.data.PaginatedData
import com.pedrenrique.githubapp.core.data.Repository
import com.pedrenrique.githubapp.core.data.User
import com.pedrenrique.githubapp.core.domain.ListRepositories
import com.pedrenrique.githubapp.core.domain.LoadMoreRepositories
import com.pedrenrique.githubapp.core.exceptions.EmptyResultException
import com.pedrenrique.githubapp.core.exceptions.NoMoreResultException
import com.pedrenrique.githubapp.features.common.DataState
import com.pedrenrique.githubapp.features.common.adapter.model.RepositoryModelHolder
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class RepositoriesViewModelTest {

    @Rule
    @JvmField
    var rule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    private lateinit var viewModel: RepositoriesViewModel

    @Mock
    private lateinit var listRepositories: ListRepositories
    @Mock
    private lateinit var loadMoreRepositories: LoadMoreRepositories
    @Mock
    private lateinit var observer: Observer<DataState>

    @Before
    fun setUp() {
        viewModel = RepositoriesViewModel(listRepositories, loadMoreRepositories)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `should list repositories once`() {
        runBlocking {
            val content = listOf(
                Repository(
                    "repository", "user/repository",
                    "description", 1, 1, User("", "")
                )
            )
            `when`(listRepositories.invoke())
                .thenReturn(PaginatedData(1, content))

            viewModel.state.observeForever(observer)
            assertEquals(viewModel.page, 0)
            viewModel.load()
            assertEquals(viewModel.page, 1)

            val data = content.map { RepositoryModelHolder(it) }

            verify(observer).onChanged(DataState.Pending)
            verify(observer).onChanged(DataState.Loaded(data))

            verify(listRepositories).invoke()

            viewModel.load()

            verifyNoMoreInteractions(observer)
            verifyNoMoreInteractions(listRepositories)
            verifyZeroInteractions(loadMoreRepositories)
        }
    }

    @Test
    fun `should load more repositories`() {
        runBlocking {
            val repository1 = RepositoryModelHolder(
                Repository(
                    "repository1", "user1/repository1",
                    "description1", 1, 1, User("1", "1")
                )
            )
            val repository2 = RepositoryModelHolder(
                Repository(
                    "repository2", "user2/repository2",
                    "repository2", 2, 2, User("2", "2")
                )
            )

            `when`(listRepositories.invoke())
                .thenReturn(PaginatedData(1, listOf(repository1.repo)))

            `when`(loadMoreRepositories.invoke(LoadMoreRepositories.Params(1)))
                .thenReturn(PaginatedData(2, listOf(repository2.repo)))

            viewModel.state.observeForever(observer)

            viewModel.load()

            verify(observer, times(2)).onChanged(any(DataState::class.java))
            verify(listRepositories).invoke()
            assertEquals(viewModel.page, 1)

            viewModel.loadMore()

            assertEquals(viewModel.page, 2)
            verify(observer).onChanged(DataState.NextPending(listOf(repository1)))
            verify(observer).onChanged(DataState.Loaded(listOf(repository1, repository2)))
            verify(loadMoreRepositories).invoke(LoadMoreRepositories.Params(1))

            verifyNoMoreInteractions(observer)
            verifyNoMoreInteractions(listRepositories)
            verifyNoMoreInteractions(loadMoreRepositories)
        }
    }

    @Test
    fun `should fail when try to load repositories`() {
        runBlocking {
            val exception = Exception("error!")
            `when`(listRepositories.invoke())
                .thenThrow(exception)

            viewModel.state.observeForever(observer)
            viewModel.load()

            verify(observer).onChanged(DataState.Pending)
            verify(observer).onChanged(DataState.Failed(exception))
            verify(listRepositories).invoke()
            assertEquals(viewModel.page, 0)

            verifyNoMoreInteractions(observer)
            verifyNoMoreInteractions(listRepositories)
            verifyNoMoreInteractions(loadMoreRepositories)
        }
    }

    @Test
    fun `should fail when try to load more repositories`() {
        runBlocking {
            val repository1 = RepositoryModelHolder(
                Repository(
                    "repository1", "user1/repository1",
                    "description1", 1, 1, User("1", "1")
                )
            )

            `when`(listRepositories.invoke())
                .thenReturn(PaginatedData(1, listOf(repository1.repo)))

            val exception = Exception("error!")
            `when`(loadMoreRepositories.invoke(LoadMoreRepositories.Params(1)))
                .thenThrow(exception)

            viewModel.state.observeForever(observer)
            viewModel.load()

            verify(observer, times(2)).onChanged(any(DataState::class.java))
            verify(listRepositories).invoke()

            viewModel.loadMore()

            verify(observer).onChanged(any(DataState.NextPending::class.java))
            verify(observer).onChanged(DataState.NextFailed(exception, listOf(repository1)))
            verify(loadMoreRepositories).invoke(LoadMoreRepositories.Params(1))
            assertEquals(viewModel.page, 1)

            verifyNoMoreInteractions(observer)
            verifyNoMoreInteractions(listRepositories)
            verifyNoMoreInteractions(loadMoreRepositories)
        }
    }

    @Test
    fun `should return empty repositories`() {
        runBlocking {
            val exception = EmptyResultException()
            `when`(listRepositories.invoke())
                .thenThrow(exception)

            viewModel.state.observeForever(observer)
            viewModel.load()

            assertEquals(viewModel.page, 0)
            verify(observer).onChanged(DataState.Pending)
            verify(observer).onChanged(DataState.Empty)
            verify(listRepositories).invoke()

            verifyNoMoreInteractions(observer)
            verifyNoMoreInteractions(listRepositories)
            verifyZeroInteractions(loadMoreRepositories)
        }
    }

    @Test
    fun `should reach the end of repository list`() {
        runBlocking {
            val repository1 = RepositoryModelHolder(
                Repository(
                    "repository1", "user1/repository1",
                    "description1", 1, 1, User("1", "1")
                )
            )
            val repository2 = RepositoryModelHolder(
                Repository(
                    "repository2", "user2/repository2",
                    "repository2", 2, 2, User("2", "2")
                )
            )

            `when`(listRepositories.invoke())
                .thenReturn(PaginatedData(1, listOf(repository1.repo)))

            `when`(loadMoreRepositories.invoke(LoadMoreRepositories.Params(1)))
                .thenReturn(PaginatedData(2, listOf(repository2.repo)))

            val exception = NoMoreResultException()
            `when`(loadMoreRepositories.invoke(LoadMoreRepositories.Params(2)))
                .thenThrow(exception)

            viewModel.state.observeForever(observer)
            viewModel.load()
            viewModel.loadMore()

            verify(observer).onChanged(DataState.Pending)
            verify(observer).onChanged(any(DataState.NextPending::class.java))
            verify(observer, times(2)).onChanged(any(DataState.Loaded::class.java))
            verifyNoMoreInteractions(observer)

            verify(listRepositories).invoke()
            verify(loadMoreRepositories).invoke(LoadMoreRepositories.Params(1))

            viewModel.loadMore()

            verify(observer, times(2)).onChanged(any(DataState.NextPending::class.java))
            verify(observer).onChanged(DataState.Completed(listOf(repository1, repository2)))
            verify(loadMoreRepositories).invoke(LoadMoreRepositories.Params(2))
            assertEquals(viewModel.page, 2)

            verifyNoMoreInteractions(observer)
            verifyNoMoreInteractions(listRepositories)
            verifyNoMoreInteractions(loadMoreRepositories)
        }
    }
}