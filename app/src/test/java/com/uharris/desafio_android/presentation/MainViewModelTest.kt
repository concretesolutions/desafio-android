package com.uharris.desafio_android.presentation

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockito_kotlin.*
import com.uharris.desafio_android.data.base.Failure
import com.uharris.desafio_android.data.base.Result
import com.uharris.desafio_android.domain.models.Repository
import com.uharris.desafio_android.domain.models.reponse.RepositoryResponse
import com.uharris.desafio_android.domain.usecases.actions.FetchRepositories
import com.uharris.desafio_android.factory.ResponseFactory
import com.uharris.desafio_android.presentation.sections.main.MainViewModel
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
class MainViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var fetchRepositories: FetchRepositories
    @Mock
    private lateinit var observerRepositories: Observer<Resource<List<Repository>>>

    @Captor
    private lateinit var captorRepository: KArgumentCaptor<(Result<RepositoryResponse>) -> Unit>

    private lateinit var mainViewModel: MainViewModel

    @Before
    fun setup(){
        fetchRepositories = mock()
        observerRepositories = mock()

        captorRepository = argumentCaptor()

        val application = Mockito.mock(Application::class.java)

        mainViewModel = MainViewModel(fetchRepositories, application)

    }

    @Test
    fun fetchRepositoriesReturnLoadingState(){
        mainViewModel.repositoriesLiveData.observeForever(observerRepositories)

        mainViewModel.fetchRepositories(any())

        assert(mainViewModel.repositoriesLiveData.value?.status == ResourceState.LOADING)
    }

    @Test
    fun fetchRepositoriesReturnSuccessData(){
        val data = ResponseFactory.makeRepositoryResponseObject()
        val result = Result.Success(data)
        mainViewModel.repositoriesLiveData.observeForever(observerRepositories)

        mainViewModel.fetchRepositories(any())

        runBlocking { verify(fetchRepositories).invoke(any(), captorRepository.capture())}
        captorRepository.firstValue.invoke(result)

        assert(mainViewModel.repositoriesLiveData.value?.data == data.items)
    }

    @Test
    fun fetchRepositoriesReturnFailure(){
        mainViewModel.repositoriesLiveData.observeForever(observerRepositories)

        mainViewModel.fetchRepositories(any())

        runBlocking { verify(fetchRepositories).invoke(any(), captorRepository.capture()) }
        captorRepository.firstValue.invoke(Result.Error(Failure.ServerError))

        assert(mainViewModel.repositoriesLiveData.value?.status == ResourceState.ERROR)
    }
}