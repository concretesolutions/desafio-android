package com.uharris.desafio_android.domain

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import com.uharris.desafio_android.data.RepositoriesRemote
import com.uharris.desafio_android.data.base.Result
import com.uharris.desafio_android.domain.usecases.actions.FetchRepositories
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
class FetchRepositoriesTest {

    @Mock
    private lateinit var repositoriesRemote: RepositoriesRemote

    private lateinit var fetchRepositories: FetchRepositories

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)

        fetchRepositories = FetchRepositories(repositoriesRemote)
    }

    @Test
    fun fetchRepositories(){
        runBlocking {
            val data = Result.Success(ResponseFactory.makeRepositoryResponseObject())
            val page = DataFactory.randomInt()

            whenever(repositoriesRemote.getRepositories(page)).thenReturn(data)

            fetchRepositories.run(FetchRepositories.Params(page))

            verify(repositoriesRemote).getRepositories(page)
            verifyNoMoreInteractions(repositoriesRemote)
        }
    }
}