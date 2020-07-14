package com.bassul.mel.app.feature.repositoriesList.repository

import com.bassul.mel.app.endpoint.GithubAPI
import com.bassul.mel.app.feature.repositoriesList.RepoListContract
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations


internal class RepoRepositoryImplTest {

    @Mock
    lateinit var githubAPI: GithubAPI

    lateinit var repoRepository: RepoListContract.Repository

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        repoRepository = RepoRepositoryImpl(githubAPI)
    }

    fun readRepositoryJson() {

    }
}