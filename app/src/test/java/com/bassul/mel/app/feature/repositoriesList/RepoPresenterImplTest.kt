package com.bassul.mel.app.feature.repositoriesList

import com.bassul.mel.app.feature.repositoriesList.RepositoryMock.Companion.errorMessageRepositoryMock
import com.bassul.mel.app.feature.repositoriesList.RepositoryMock.Companion.listItemMock
import com.bassul.mel.app.feature.repositoriesList.presenter.RepoPresenterImpl
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

internal class RepoPresenterImplTest {

    private lateinit var repoPresenter: RepoListContract.Presenter

    @Mock
    lateinit var repoView: RepoListContract.View

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repoPresenter = RepoPresenterImpl(repoView)
    }

    @Test
    fun showCard() {
        repoPresenter.showCard(listItemMock())
        verify(repoView).showCard(listItemMock())
    }

    @Test
    fun errorShowCard(){
        repoPresenter.errorShowCard(errorMessageRepositoryMock())
        verify(repoView).showErrorRepoList(errorMessageRepositoryMock())
    }
}