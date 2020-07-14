package com.bassul.mel.app.feature.pullRequestList.repository

import com.bassul.mel.app.callback.RepositorySelectedRepositoriesCallback
import com.bassul.mel.app.endpoint.GithubAPI
import com.bassul.mel.app.feature.pullRequestList.PullRequestListContract
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.MockitoAnnotations

internal class PullRequestRepositoryImplTest {


    @Mock
    lateinit var githubAPI: GithubAPI

    lateinit var pullRequestRepository: PullRequestListContract.Repository

    @Captor
    lateinit var repositorySelectedRepositoriesCallback: ArgumentCaptor<RepositorySelectedRepositoriesCallback>


    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        pullRequestRepository = PullRequestRepositoryImpl(githubAPI)
    }

    @Test
    fun readPullRequestJson() {
     //   pullRequestRepository.readPullRequestJson("login", "repositoryName", capture(repositorySelectedRepositoriesCallback))
    //    verify(githubAPI).fetchPullRequestData("login", "repository name")
    }
}