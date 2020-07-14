package com.bassul.mel.app.feature.repositoriesList

import com.bassul.mel.app.domain.Item
import com.bassul.mel.app.domain.Owner
import com.bassul.mel.app.feature.repositoriesList.RepositoryMock.Companion.errorMessageRepositoryMock
import com.bassul.mel.app.feature.repositoriesList.RepositoryMock.Companion.listItemMock
import com.bassul.mel.app.feature.repositoriesList.presenter.RepoPresenterImpl
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

internal class RepoPresenterImplTest {

    lateinit var repositoriesPresenter: RepositoriesListContract.Presenter

    @Mock
    lateinit var repositoriesView: RepositoriesListContract.View

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repositoriesPresenter = RepoPresenterImpl(repositoriesView)
    }

    @Test
    fun showCard() {
        repositoriesPresenter.showCard(listItemMock())
        verify(repositoriesView).showCard(listItemMock())
    }

    @Test
    fun errorShowCard(){
        repositoriesPresenter.errorShowCard(errorMessageRepositoryMock())
        verify(repositoriesView).showErrorCard(errorMessageRepositoryMock())
    }
}