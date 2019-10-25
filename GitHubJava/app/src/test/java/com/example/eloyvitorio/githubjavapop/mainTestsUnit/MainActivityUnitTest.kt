package com.example.eloyvitorio.githubjavapop.mainTestsUnit

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.eloyvitorio.githubjavapop.di.appModule
import com.example.eloyvitorio.githubjavapop.data.model.Owner
import com.example.eloyvitorio.githubjavapop.data.model.Repository
import com.example.eloyvitorio.githubjavapop.data.network.CallBackRepo
import com.example.eloyvitorio.githubjavapop.data.network.RepositoriesAPI
import com.example.eloyvitorio.githubjavapop.ui.repositories.RepositoriesViewModel
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.capture
import com.nhaarman.mockitokotlin2.verify
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.get
import org.koin.test.inject
import org.koin.test.mock.declareMock
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainActivityUnitTest : KoinTest {
    private val viewModel by inject<RepositoriesViewModel>()

    @get:Rule
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Captor
    private lateinit var callbackRepo: ArgumentCaptor<CallBackRepo>

    @Before
    fun setUp() {
        startKoin {
            modules(appModule)
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun whenFetchRepositories_shouldPostRepositoriesList() {
        val list = getMockList()
        declareMock<RepositoriesAPI>()
        val repositoryApiMock = get<RepositoriesAPI>()

        viewModel.fetchRepositories()

        callbackRepo.apply {
            verify(repositoryApiMock).getJavaRepositories(any(), capture(this))
            this.value.onSucessGetRepository(list)
            assertEquals(viewModel.repositoryList.value, list)
        }
    }

    @Test
    fun whenFetchRepositoriesWithSuccess_shouldNoPostErrorMessage() {
        val list = getMockList()
        declareMock<RepositoriesAPI>()
        val repositoryApiMock = get<RepositoriesAPI>()

        viewModel.fetchRepositories()

        callbackRepo.apply {
            verify(repositoryApiMock).getJavaRepositories(any(), capture(this))
            this.value.onSucessGetRepository(list)
            assertEquals(viewModel.error.value, null)
        }
    }

    @Test
    fun whenFetchRepositoriesWithError_shouldPostErrorMessage() {
        val mockMessage = "Um erro ocorreu. \nTente carregar novamente."
        declareMock<RepositoriesAPI>()
        val repositoryApiMock = get<RepositoriesAPI>()

        viewModel.fetchRepositories()

        callbackRepo.apply {
            verify(repositoryApiMock).getJavaRepositories(any(), capture(this))
            this.value.onErrorGetRepository(mockMessage)
            assertEquals(viewModel.error.value, mockMessage)
        }
    }

    private fun getMockList(): List<Repository> {
        val owner = Owner(login = "CyC2018",
                avatarUrl = "https://avatars3.githubusercontent.com/u/36260787?v=4")
        return arrayListOf(
                Repository(id = 1,
                        name = "CS-Notes",
                        fullName = "CyC2018/CS-Notes",
                        owner = owner,
                        description = ":books:",
                        stargazersCount = 64689,
                        forks = 19816)
        )
    }
}