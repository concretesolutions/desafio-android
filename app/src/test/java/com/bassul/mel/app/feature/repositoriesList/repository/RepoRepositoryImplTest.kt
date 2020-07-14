package com.bassul.mel.app.feature.repositoriesList.repository

import com.bassul.mel.app.endpoint.GithubAPI
import com.bassul.mel.app.feature.pullRequestList.PullRequestListContract
import com.bassul.mel.app.feature.pullRequestList.repository.PullRequestRepositoryImpl
import com.bassul.mel.app.feature.repositoriesList.RepositoriesListContract
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations


internal class RepoRepositoryImplTest {

    @Mock
    lateinit var githubAPI: GithubAPI

    lateinit var repoRepository: RepositoriesListContract.Repository

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        repoRepository = RepoRepositoryImpl(githubAPI)
    }

    fun readRepositoryJson() {

    }
}