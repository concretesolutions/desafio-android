package com.uharris.desafio_android.presentation

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockito_kotlin.*
import com.uharris.desafio_android.data.base.Failure
import com.uharris.desafio_android.data.base.Result
import com.uharris.desafio_android.domain.models.PullRequest
import com.uharris.desafio_android.domain.usecases.actions.FetchPullRequest
import com.uharris.desafio_android.factory.DataFactory
import com.uharris.desafio_android.factory.ResponseFactory
import com.uharris.desafio_android.presentation.sections.pull.PullRequestViewModel
import com.uharris.desafio_android.presentation.state.Resource
import com.uharris.desafio_android.presentation.state.ResourceState
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito

@RunWith(JUnit4::class)
class PullRequestViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var fetchPullRequest: FetchPullRequest
    @Mock
    private lateinit var observerPullRequests: Observer<Resource<List<PullRequest>>>

    @Captor
    private lateinit var captorPullRequest: KArgumentCaptor<(Result<List<PullRequest>>) -> Unit>

    private lateinit var pullRequestViewModel: PullRequestViewModel

    @Before
    fun setup(){
        fetchPullRequest = mock()
        observerPullRequests = mock()

        captorPullRequest = argumentCaptor()

        val application = Mockito.mock(Application::class.java)

        pullRequestViewModel = PullRequestViewModel(fetchPullRequest, application)
    }

    @Test
    fun fetchPullRequestsReturnLoadingState(){
        pullRequestViewModel.pullRequestsLiveData.observeForever(observerPullRequests)

        pullRequestViewModel.fetchPullRequest(DataFactory.randomString(), DataFactory.randomString())

        assert(pullRequestViewModel.pullRequestsLiveData.value?.status == ResourceState.LOADING)
    }

    @Test
    fun fetchPullRequestsReturnSuccessData(){
        val data = ResponseFactory.makeullRequestList()
        val result = Result.Success(data)
        pullRequestViewModel.pullRequestsLiveData.observeForever(observerPullRequests)

        pullRequestViewModel.fetchPullRequest(DataFactory.randomString(), DataFactory.randomString())

        runBlocking { verify(fetchPullRequest).invoke(any(), captorPullRequest.capture())}
        captorPullRequest.firstValue.invoke(result)

        assert(pullRequestViewModel.pullRequestsLiveData.value?.data == data)
    }

    @Test
    fun fetchPullRequestsReturnFailure(){
        pullRequestViewModel.pullRequestsLiveData.observeForever(observerPullRequests)

        pullRequestViewModel.fetchPullRequest(DataFactory.randomString(), DataFactory.randomString())

        runBlocking { verify(fetchPullRequest).invoke(any(), captorPullRequest.capture()) }
        captorPullRequest.firstValue.invoke(Result.Error(Failure.ServerError))

        assert(pullRequestViewModel.pullRequestsLiveData.value?.status == ResourceState.ERROR)
    }
}