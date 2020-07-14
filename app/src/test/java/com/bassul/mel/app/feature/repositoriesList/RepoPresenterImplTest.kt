package com.bassul.mel.app.feature.repositoriesList

import com.bassul.mel.app.domain.Item
import com.bassul.mel.app.domain.Owner
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
    fun openListPullRequest() {
    }

    fun listItemMock() : ArrayList<Item>{
        var list: ArrayList<Item> = ArrayList()
        list.add(itemMock())
        return list
    }

    fun itemMock() : Item{
        return Item(1, "Name", ownerMock(), "300", "200", "description", "url")
    }

    fun ownerMock() : Owner{
        return Owner("logn", 2, "url")
    }
}