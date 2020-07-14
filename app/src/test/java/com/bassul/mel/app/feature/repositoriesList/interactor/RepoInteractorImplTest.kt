package com.bassul.mel.app.feature.repositoriesList.interactor

import com.bassul.mel.app.callback.RepositotyAllRepositoriesCallback
import com.bassul.mel.app.feature.repositoriesList.RepoListContract
import com.bassul.mel.app.feature.repositoriesList.RepositoryMock.Companion.listItemMock
import com.bassul.mel.app.feature.repositoriesList.RepositoryMock.Companion.repositoriesListReponseMock
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

internal class RepoInteractorImplTest {

    @Mock
    lateinit var repoRepository:  RepoListContract.Repository
    @Mock
    lateinit var repoPresenter: RepoListContract.Presenter

    lateinit var repoInteractor: RepoListContract.Interactor

    @Captor
     var repositotyAllRepositoriesCallback = ArgumentCaptor.forClass(RepositotyAllRepositoriesCallback::class.java)


    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        repoInteractor = RepoInteractorImpl(repoPresenter, repoRepository)
    }

    @Test
    fun loadRepositories() {
        repoInteractor.loadRepositories(1)
        verify(repoRepository).readRepositoryJson(1, repositotyAllRepositoriesCallback.capture())
    }

    @Test
    fun convertGithubRepositoriesListResponseToRepositoriesList(){
       var repoListResponse =  repoInteractor.convertGithubRepositoriesListResponseToRepositoriesList(repositoriesListReponseMock())
        Assert.assertEquals(repoListResponse, listItemMock())
    }

}