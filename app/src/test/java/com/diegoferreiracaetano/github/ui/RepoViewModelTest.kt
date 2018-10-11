package com.diegoferreiracaetano.github.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.diegoferreiracaetano.domain.NetworkState
import com.diegoferreiracaetano.domain.repo.Repo
import com.diegoferreiracaetano.domain.repo.interactor.CallbackRepoInteractor
import com.diegoferreiracaetano.domain.repo.interactor.GetListRepoInteractor
import com.diegoferreiracaetano.github.mock.GitHubFake
import com.diegoferreiracaetano.github.mock.RepoDataSource
import com.diegoferreiracaetano.github.ui.repo.RepoViewModel
import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class RepoViewModelTest{

    @Mock private lateinit var getRepoInteractor: GetListRepoInteractor
    @Mock private lateinit var callback: CallbackRepoInteractor

    private lateinit var viewModel: RepoViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }


    @Test
    @Throws(Exception::class)
    fun `Given repos, When load repo, Should update result`() {

        // Given

        val repo = emptyList<Repo>()
        val dataSource = RepoDataSource.RepoDataSourceFactory(repo)

        // When

        `when`(getRepoInteractor.execute()).thenReturn(dataSource)
        viewModel = RepoViewModel(getRepoInteractor,callback)

        // Should

        viewModel.result.observeForever{
            assertThat(it, `is`(repo))
        }

    }

    @Test
    @Throws(Exception::class)
    fun `Given repos, When empty, Should update result`() {

        // Given

        val repo = listOf(GitHubFake.repo)
        val dataSource = RepoDataSource.RepoDataSourceFactory(repo)

        // When

        `when`(getRepoInteractor.execute()).thenReturn(dataSource)
        viewModel = RepoViewModel(getRepoInteractor,callback)

        // Should

        viewModel.result.observeForever{
            assertThat(it, `is`(repo))
        }
    }

    @Test
    @Throws(Exception::class)
    fun `Given callback network, When load network, Should update result`() {

        // Given

        val networkState = MutableLiveData<NetworkState>()
        networkState.postValue(NetworkState.LOADED)
        val dataSource = RepoDataSource.RepoDataSourceFactory(emptyList())

        // When

        `when`(getRepoInteractor.execute()).thenReturn(dataSource)
        `when`(callback.networkState).thenReturn(networkState)

        viewModel = RepoViewModel(getRepoInteractor,callback)
        // Should

        viewModel.networkState.observeForever{
            assertThat(it, `is`(networkState.value))
        }
    }
}