package com.bassul.mel.app.feature.repositoriesList.interactor

import com.bassul.mel.app.callback.RepositotyAllRepositoriesCallback
import com.bassul.mel.app.feature.repositoriesList.RepositoriesListContract
import com.bassul.mel.app.feature.repositoriesList.RepositoryMock.Companion.itemResponseMock
import com.bassul.mel.app.feature.repositoriesList.RepositoryMock.Companion.listItemMock
import com.bassul.mel.app.feature.repositoriesList.RepositoryMock.Companion.listResponseMock
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
    lateinit var repositoriesRepository:  RepositoriesListContract.Repository
    @Mock
    lateinit var repositoriesPresenter: RepositoriesListContract.Presenter

    lateinit var repositoriesInteractor: RepositoriesListContract.Interactor

    @Captor
    lateinit var repositotyAllRepositoriesCallback: ArgumentCaptor<RepositotyAllRepositoriesCallback>


    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        repositoriesInteractor = RepoInteractorImpl(repositoriesPresenter, repositoriesRepository)
    }

    @Test
    fun loadRepositories() {
     //   repositoriesInteractor.loadRepositories(1)
     //   verify(repositoriesRepository).readRepositoryJson(1, capture(repositotyAllRepositoriesCallback))
    }

    @Test
    fun convertGithubRepositoriesListResponseToRepositoriesList(){
       var repoListResponse =  repositoriesInteractor.convertGithubRepositoriesListResponseToRepositoriesList(repositoriesListReponseMock())
        Assert.assertEquals(repoListResponse, listItemMock())
    }

}