package com.uharris.desafio_android.domain

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyZeroInteractions
import com.nhaarman.mockito_kotlin.whenever
import com.uharris.desafio_android.data.RepositoriesRemote
import com.uharris.desafio_android.data.base.Result
import com.uharris.desafio_android.domain.models.PullRequest
import com.uharris.desafio_android.domain.usecases.actions.FetchPullRequest
import com.uharris.desafio_android.factory.DataFactory
import com.uharris.desafio_android.factory.ResponseFactory
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FetchPullRequestTest {

    @Mock
    private lateinit var repositoriesRemote: RepositoriesRemote

    private lateinit var fetchPullRequest: FetchPullRequest

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)

        fetchPullRequest = FetchPullRequest(repositoriesRemote)
    }

    @Test
    fun fetchPullRequest(){
        runBlocking {
            val data = ResponseFactory.makeullRequestList()
            val creator = DataFactory.randomString()
            val repository = DataFactory.randomString()

            whenever(repositoriesRemote.getRepositoriesPullRequests(creator, repository)).thenReturn(Result.Success(data))

            fetchPullRequest.run(FetchPullRequest.Params(creator, repository))

            verify(repositoriesRemote).getRepositoriesPullRequests(creator, repository)
            verifyZeroInteractions(repositoriesRemote)
        }
    }
}