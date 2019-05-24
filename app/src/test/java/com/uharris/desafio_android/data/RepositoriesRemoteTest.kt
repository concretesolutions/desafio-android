package com.uharris.desafio_android.data

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verifyZeroInteractions
import com.nhaarman.mockito_kotlin.whenever
import com.uharris.desafio_android.data.base.Failure
import com.uharris.desafio_android.data.base.Result
import com.uharris.desafio_android.data.remote.RepositoriesRemoteImpl
import com.uharris.desafio_android.data.services.RepositoriesServices
import com.uharris.desafio_android.domain.models.PullRequest
import com.uharris.desafio_android.domain.models.reponse.RepositoryResponse
import com.uharris.desafio_android.factory.DataFactory
import com.uharris.desafio_android.factory.ResponseFactory
import com.uharris.desafio_android.presentation.base.NetworkHandler
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.runBlocking
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
class RepositoriesRemoteTest {

    @Mock
    private lateinit var service: RepositoriesServices
    @Mock
    private lateinit var networkHandler: NetworkHandler

    private lateinit var repositoriesRemote: RepositoriesRemote

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)

        repositoriesRemote = RepositoriesRemoteImpl(networkHandler, service)
    }

    @Test
    fun getRepositoriesNetworkFailure(){
        given { networkHandler.isConnected }.willReturn(false)

        runBlocking { repositoriesRemote.getRepositories(DataFactory.randomInt()) }

        verifyZeroInteractions(service)
    }

    @Test
    fun getRepositoriesNetworkFailureNull(){
        given { networkHandler.isConnected }.willReturn(null)

        runBlocking { repositoriesRemote.getRepositories(DataFactory.randomInt()) }

        verifyZeroInteractions(service)
    }

    @Test
    fun getRepositoriesServerError() {
        val deferred = CompletableDeferred<Response<RepositoryResponse>>()
        deferred.complete(Response.error(400, ResponseBody.create(MultipartBody.FORM, "Error")))

        whenever(service.getRepositories(any())).thenReturn(deferred)

        given { networkHandler.isConnected }.willReturn(true)

        val repositoryResponse = runBlocking { repositoriesRemote.getRepositories(DataFactory.randomInt()) }

        assertEquals(Result.Error(Failure.ServerError), repositoryResponse)
    }

    @Test
    fun getRepositoriesSuccess() {
        val data = ResponseFactory.makeRepositoryResponseObject()
        val deferred = CompletableDeferred<Response<RepositoryResponse>>()
        deferred.complete(Response.success(data))

        whenever(service.getRepositories(any())).thenReturn(deferred)

        given { networkHandler.isConnected }.willReturn(true)

        val repositoryResponse = runBlocking { repositoriesRemote.getRepositories(DataFactory.randomInt()) }

        assertEquals(Result.Success(data), repositoryResponse)
    }

    @Test
    fun getPullRequestServerError(){
        val deferred = CompletableDeferred<Response<List<PullRequest>>>()
        deferred.complete(Response.error(400, ResponseBody.create(MultipartBody.FORM, "Error")))

        whenever(service.getRepositoryPullRequests(any(), any())).thenReturn(deferred)

        given { networkHandler.isConnected }.willReturn(true)

        val pullRequests = runBlocking { repositoriesRemote.getRepositoriesPullRequests(DataFactory.randomString(), DataFactory.randomString()) }

        assertEquals(Result.Error(Failure.ServerError), pullRequests)
    }

    @Test
    fun getPullRequestSuccess(){
        val data = ResponseFactory.makeullRequestList()
        val deferred = CompletableDeferred<Response<List<PullRequest>>>()
        deferred.complete(Response.success(data))

        whenever(service.getRepositoryPullRequests(any(), any())).thenReturn(deferred)

        given { networkHandler.isConnected }.willReturn(true)

        val pullRequests = runBlocking { repositoriesRemote.getRepositoriesPullRequests(DataFactory.randomString(), DataFactory.randomString()) }

        assertEquals(Result.Success(data), pullRequests)
    }
}