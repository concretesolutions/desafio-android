package com.bassul.mel.app.feature.pullRequestList.interactor

import com.bassul.mel.app.callback.RepositorySelectedRepositoriesCallback
import com.bassul.mel.app.feature.pullRequestList.PullRequestMock.Companion.listPullRequestResponseMock
import com.bassul.mel.app.feature.pullRequestList.PullRequestMock.Companion.pullRequestListMock
import com.bassul.mel.app.feature.pullRequestList.PullRequestListContract
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.MockitoAnnotations

internal class PullRequestInteractorImplTest{

    lateinit var pullRequestInteractor: PullRequestListContract.Interactor
    @Mock
    lateinit var pullRequestPresenter: PullRequestListContract.Presenter
    @Mock
    lateinit var pullRequestRepository: PullRequestListContract.Repository

    @Captor
    lateinit var repositorySelectedRepositoriesCallback: ArgumentCaptor<RepositorySelectedRepositoriesCallback>

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        pullRequestInteractor = PullRequestInteractorImpl(pullRequestPresenter, pullRequestRepository)
    }

    @Test
    fun getSelectedItem(){
        pullRequestInteractor.getSelectedItem("login", "repository")
      //  verify(pullRequestRepository).readPullRequestJson("login", "repository", RepositorySelectedRepositoriesCallback)
    }

    @Test
    fun convertPullRequestListResponseToPullResponse(){
       var arrayListPullRequest =  pullRequestInteractor.convertPullRequestListResponseToPullResponse(listPullRequestResponseMock())
        Assert.assertEquals(arrayListPullRequest, pullRequestListMock())

    }

}