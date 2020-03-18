package com.rafaelmfer.consultinggithub

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.rafaelmfer.consultinggithub.mvvm.model.GitHubRepository
import com.rafaelmfer.consultinggithub.mvvm.model.pullrequests.GitPullRequestResponse
import com.rafaelmfer.consultinggithub.mvvm.model.repositories.GitRepositoriesResponse
import com.rafaelmfer.consultinggithub.mvvm.model.repositories.Item
import com.rafaelmfer.consultinggithub.mvvm.viewmodel.GitHubRepositoriesViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GitHubRepositoriesViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @MockK
    lateinit var repository: GitHubRepository

    lateinit var viewModel: GitHubRepositoriesViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel = GitHubRepositoriesViewModel(repository)
    }

    @Test
    fun getRepositoriesList_successResponse() {
        val response: GitRepositoriesResponse = mockk()
        val repositoriesList = listOf<Item>()

        every { repository.getRepositoriesList(viewModel,1) } answers { viewModel.onSuccess(response) }
        every { response.items } returns repositoriesList

        viewModel.getRepositoriesList(1)

        assertEquals(GitHubRepositoriesViewModel.Command.HideLoading, viewModel.command.value)
        assertEquals(repositoriesList, viewModel.gitHubRepositories.value)
    }

    @Test
    fun getPullRequestsList_successResponse() {
        val response: GitPullRequestResponse = mockk()
        val pullRequestsList = listOf<GitPullRequestResponse>()

        every { repository.getPullRequestsList(viewModel,"", "", 1) } answers { viewModel.onSuccess(listOf(response))}
        every { listOf(response) } returns pullRequestsList

        viewModel.getPullRequestsList("", "", 1)

        assertEquals(GitHubRepositoriesViewModel.Command.HideLoading, viewModel.command.value)
        assertEquals(pullRequestsList, viewModel.gitHubRepositories.value)
    }

    @Test
    fun getRepositoriesList_errorResponse() {
        val error = Exception()

        every { repository.getRepositoriesList(viewModel,1) } answers { viewModel.onError(error) }

        viewModel.getRepositoriesList(1)

        assertEquals(GitHubRepositoriesViewModel.Command.HideLoading, viewModel.command.value)
        assertEquals(error, viewModel.errorLiveData.value)
    }

    @Test
    fun getPullRequestsList_errorResponse() {
        val error = Exception()

        every { repository.getPullRequestsList(viewModel,"", "", 1) } answers { viewModel.onError(error) }

        viewModel.getPullRequestsList("", "", 1)

        assertEquals(GitHubRepositoriesViewModel.Command.HideLoading, viewModel.command.value)
        assertEquals(error, viewModel.errorLiveData.value)
    }
}