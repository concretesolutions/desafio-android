package com.diegoferreiracaetano.github.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.diegoferreiracaetano.domain.NetworkState
import com.diegoferreiracaetano.domain.pull.Pull
import com.diegoferreiracaetano.domain.pull.interactor.CallbackPullInteractor
import com.diegoferreiracaetano.domain.pull.interactor.GetListPullInteractor
import com.diegoferreiracaetano.github.mock.GitHubFake
import com.diegoferreiracaetano.github.mock.PullDataSource
import com.diegoferreiracaetano.github.ui.pull.PullViewModel
import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class PullViewModelTest{

    @Mock private lateinit var getPullInteractor: GetListPullInteractor
    @Mock private lateinit var callback: CallbackPullInteractor

    private lateinit var viewModel: PullViewModel
    private val param = Pair(GitHubFake.owner.name,GitHubFake.repo.name)

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }


    @Test
    @Throws(Exception::class)
    fun `Given pulls, When load pull, Should update result`() {

        // Given

        val pull = emptyList<Pull>()
        val dataSource = PullDataSource.PullDataSourceFactory(pull)

        // When

        `when`(getPullInteractor.execute(GetListPullInteractor.Request(param.first,param.second))).thenReturn(dataSource)
        viewModel = PullViewModel(getPullInteractor,callback)

        // Should

        viewModel.result.observeForever{
            assertThat(it, `is`(pull))
        }

    }

    @Test
    @Throws(Exception::class)
    fun `Given pulls, When empty, Should update result`() {

        // Given

        val pull = listOf(GitHubFake.pull)
        val dataSource = PullDataSource.PullDataSourceFactory(pull)

        // When

        `when`(getPullInteractor.execute(GetListPullInteractor.Request(param.first,param.second))).thenReturn(dataSource)
        viewModel = PullViewModel(getPullInteractor,callback)

        // Should

        viewModel.result.observeForever{
            assertThat(it, `is`(pull))
        }
    }

    @Test
    @Throws(Exception::class)
    fun `Given callback network, When load network, Should update result`() {

        // Given

        val networkState = MutableLiveData<NetworkState>()
        networkState.postValue(NetworkState.LOADED)

        val pull = emptyList<Pull>()
        val dataSource = PullDataSource.PullDataSourceFactory(pull)

        // When
        `when`(getPullInteractor.execute(GetListPullInteractor.Request(param.first,param.second))).thenReturn(dataSource)
        `when`(callback.networkState).thenReturn(networkState)
        viewModel = PullViewModel(getPullInteractor,callback)

        // Should

        viewModel.networkState.observeForever{
            assertThat(it, `is`(networkState.value))
        }
    }
}