package com.losingtimeapps.domainTest

import com.losingtimeapps.common.BaseView
import com.losingtimeapps.domain.Error
import com.losingtimeapps.domain.entity.Author
import com.losingtimeapps.domain.entity.Repository
import com.losingtimeapps.domain.usercase.GetGitHubPullRequestsUseCase
import com.losingtimeapps.domain.usercase.GetGitHubRepoUseCase
import com.losingtimeapps.presentation.model.AuthorModel
import com.losingtimeapps.presentation.model.RepositoryModel
import com.losingtimeapps.presentation.ui.home.pullrequest.PullRequestView
import com.losingtimeapps.presentation.ui.home.pullrequest.PullRequestViewModel
import com.losingtimeapps.presentation.ui.home.repository.RepositoryView
import com.losingtimeapps.presentation.ui.home.repository.RepositoryViewModel
import com.losingtimeapps.presentationTest.GitHubRepositoryImpTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class PresentationTest {

    private lateinit var getGitHubRepoUseCase: GetGitHubRepoUseCase
    private lateinit var getGitHubPullRequestsUseCase: GetGitHubPullRequestsUseCase
    private lateinit var repositoryViewModel: RepositoryViewModel
    private lateinit var pullRequestViewModel: PullRequestViewModel
    private val gitHubRepositoryImpTest = GitHubRepositoryImpTest()

    private val testScheduleImp: TestScheduleImp = TestScheduleImp()

    @Mock
    lateinit var repositoryView: RepositoryView
    @Mock
    lateinit var pullRequestView: PullRequestView


    /// REPOSITORY USE CASE TEST
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getGitHubPullRequestsUseCase = GetGitHubPullRequestsUseCase(gitHubRepositoryImpTest, testScheduleImp)
        getGitHubRepoUseCase = GetGitHubRepoUseCase(gitHubRepositoryImpTest, testScheduleImp)
        repositoryViewModel = RepositoryViewModel(getGitHubRepoUseCase)
        pullRequestViewModel = PullRequestViewModel(getGitHubPullRequestsUseCase)
        repositoryViewModel.setView(repositoryView)
        pullRequestViewModel.setView(pullRequestView, "test", "test")

    }


    @Test
    fun get_repository_call_update_data() {
        gitHubRepositoryImpTest.returnError = false
        gitHubRepositoryImpTest.returnEmptyList = false

        repositoryViewModel.getRepositorys()

        Mockito.verify(repositoryView).updateRepoLiveData(Mockito.anyList())
    }

    @Test
    fun get_repository_click_item_nav_pull_Request() {
        gitHubRepositoryImpTest.returnError = false
        gitHubRepositoryImpTest.returnEmptyList = false

        repositoryViewModel.getRepositorys()
        Mockito.verify(repositoryView).updateRepoLiveData(Mockito.anyList())
        repositoryViewModel.onClickRepository(
            RepositoryModel(
                0,
                "", "", "", "0",
                AuthorModel(0, "", "")
            )
        )
        Mockito.verify(repositoryView).navigateToPullRequestView(Mockito.anyString(), Mockito.anyString())

    }

    @Test
    fun get_repository_call_error() {
        gitHubRepositoryImpTest.returnError = true
        gitHubRepositoryImpTest.returnEmptyList = false

        repositoryViewModel.getRepositorys()

        Mockito.verify(repositoryView as BaseView).showSnackbarError(Error.NetworkConnectionError)
    }

    @Test
    fun get_pull_request_call_no_data() {
        gitHubRepositoryImpTest.returnError = false
        gitHubRepositoryImpTest.returnEmptyList = true

        pullRequestViewModel.getPullRequests()

        Mockito.verify(pullRequestView as BaseView).notDataLoaded(true)
    }

    @Test
    fun get_pull_request_call_update_data() {

        gitHubRepositoryImpTest.returnError = false
        gitHubRepositoryImpTest.returnEmptyList = false

        pullRequestViewModel.getPullRequests()

        Mockito.verify(pullRequestView).updateRepoLiveData(Mockito.anyList())
    }

/*


    @Test
    fun get_repo_network_connection_error() {
        Mockito.`when`(
            gitHubRepository.getGitHubRepositories(
                Mockito.anyString(), Mockito.anyString(), Mockito.anyInt()
            )
        )
            .thenReturn(mockRepositoryBackendError.networkConnectionErrorResponse)
        getGitHubRepoUseCase.invoke("language:Java", "stars", 0, responseBoundary)

        Mockito.verify(responseBoundary).onError(Error.NetworkConnectionError)
    }

    @Test
    fun get_repo_empty_languague_error() {

        Mockito.`when`(
            gitHubRepository.getGitHubRepositories(
                Mockito.anyString(), Mockito.anyString(), Mockito.anyInt()
            )
        )
            .thenReturn(correctRepositoryResponse)
        getGitHubRepoUseCase.invoke("", "", 0, responseBoundary)

        Mockito.verify(responseBoundary).onError(Error.EmptyLanguageError)

    }

    @Test
    fun get_repo_internal_error() {
        Mockito.`when`(
            gitHubRepository.getGitHubRepositories(
                Mockito.anyString(), Mockito.anyString(), Mockito.anyInt()
            )
        ).thenReturn(mockRepositoryBackendError.internalServerErrorResponse)
        getGitHubRepoUseCase.invoke("language:Java", "stars", 0, responseBoundary)

        Mockito.verify(responseBoundary).onError(Error.InternalServerError)
    }

    @Test
    fun get_repo_list_invalid_language_error() {

        Mockito.`when`(
            gitHubRepository.getGitHubRepositories(
                Mockito.anyString(), Mockito.anyString(), Mockito.anyInt()
            )
        ).thenReturn(mockRepositoryBackendError.invalidLanguageResponse)
        getGitHubRepoUseCase.invoke("language:Java", "stars", 0, responseBoundary)

        Mockito.verify(responseBoundary).onError(Error.RepositoryInvalidLanguageError)
    }


    @Test
    fun get_gitHub_repo_list_success() {

        Mockito.`when`(
            gitHubRepository.getGitHubRepositories(
                Mockito.anyString(), Mockito.anyString(), Mockito.anyInt()
            )
        ).thenReturn(correctRepositoryResponse)
        getGitHubRepoUseCase.invoke("language:Java", "stars", 0, responseBoundary)

        Mockito.verify(responseBoundary).onGetRepository(repoList)
    }

    // PullRequest Use Case Test

    @Test
    fun get_pull_request_network_connection_error() {
        Mockito.`when`(
            gitHubRepository.getGitHubPullRequests(
                Mockito.anyString(), Mockito.anyString(), Mockito.anyInt()
            )
        )
            .thenReturn(mockPullRequestBackendError.networkConnectionErrorResponse)
        getGitHubPullRequestsUseCase.invoke("", "", 0, responseBoundary)

        Mockito.verify(responseBoundary).onError(Error.NetworkConnectionError)
    }

    @Test
    fun get_pull_request_internal_error() {
        Mockito.`when`(
            gitHubRepository.getGitHubPullRequests(
                Mockito.anyString(), Mockito.anyString(), Mockito.anyInt()
            )
        ).thenReturn(mockPullRequestBackendError.internalServerErrorResponse)
        getGitHubPullRequestsUseCase.invoke("", "", 0, responseBoundary)

        Mockito.verify(responseBoundary).onError(Error.InternalServerError)
    }

    @Test
    fun get_pull_request_list_Language_not_found_error() {

        Mockito.`when`(
            gitHubRepository.getGitHubPullRequests(
                Mockito.anyString(), Mockito.anyString(), Mockito.anyInt()
            )
        ).thenReturn(mockPullRequestBackendError.notFoundResponse)
        getGitHubPullRequestsUseCase.invoke("", "", 0, responseBoundary)

        Mockito.verify(responseBoundary).onError(Error.NotFoundError)
    }

    @Test
    fun get_pull_request_list_success() {
        Mockito.`when`(
            gitHubRepository.getGitHubPullRequests(
                Mockito.anyString(), Mockito.anyString(), Mockito.anyInt()
            )
        ).thenReturn(correctPullRequestResponse)
        getGitHubPullRequestsUseCase.invoke("", "", 0, responseBoundary)

        Mockito.verify(responseBoundary).onGetPullRequest(pullRequestList)
    }*/
}