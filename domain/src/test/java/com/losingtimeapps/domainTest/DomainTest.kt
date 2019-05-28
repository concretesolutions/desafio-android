package com.losingtimeapps.domainTest

import com.losingtimeapps.domain.Error
import com.losingtimeapps.domain.boundary.GitHubRepository
import com.losingtimeapps.domain.boundary.ResponseBoundary
import com.losingtimeapps.domain.entity.Author
import com.losingtimeapps.domain.entity.PullRequest
import com.losingtimeapps.domain.entity.Repository
import com.losingtimeapps.domain.usercase.GetGitHubPullRequestsUseCase
import com.losingtimeapps.domain.usercase.GetGitHubRepoUseCase
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.*

class DomainTest {

    private lateinit var getGitHubRepoUseCase: GetGitHubRepoUseCase
    private lateinit var getGitHubPullRequestsUseCase: GetGitHubPullRequestsUseCase
    private val testScheduleImp: TestScheduleImp = TestScheduleImp()
    @Mock
    lateinit var gitHubRepository: GitHubRepository
    @Mock
    lateinit var responseBoundary: ResponseBoundary

    private val repoList = Arrays.asList(
        Repository(
            0,
            "", "", 0, 0,
            Author(0, "", "")
        )
    )
    private val pullRequestList = Arrays.asList(
        PullRequest(
            0,
            "", "", "",
            Author(0, "", ""),
            "",
            ""
        )
    )
    private val correctRepositoryResponse = Single.just(repoList)

    private val correctPullRequestResponse = Single.just(pullRequestList)

    private val mockRepositoryBackendError = MockBackendError<List<Repository>>()

    private val mockPullRequestBackendError = MockBackendError<List<PullRequest>>()

    /// REPOSITORY USE CASE TEST
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getGitHubPullRequestsUseCase = GetGitHubPullRequestsUseCase(gitHubRepository, testScheduleImp)
        getGitHubRepoUseCase = GetGitHubRepoUseCase(gitHubRepository, testScheduleImp)
    }

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
    }
}