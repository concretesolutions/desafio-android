package com.bassul.mel.app.feature.repositoriesList


import com.bassul.mel.app.feature.pullRequestList.PullRequestListContract
import com.bassul.mel.app.feature.pullRequestList.PullRequestMock.Companion.errorMessagePullRequestMock
import com.bassul.mel.app.feature.pullRequestList.PullRequestMock.Companion.pullRequestListMock
import com.bassul.mel.app.feature.pullRequestList.presenter.PullRequestPresenterImpl
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

internal class PullRequestPresenterImplTest {

    lateinit var pullRequestPresenter: PullRequestListContract.Presenter

    @Mock
    lateinit var pullRequestView: PullRequestListContract.View

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        pullRequestPresenter = PullRequestPresenterImpl(pullRequestView)
    }

    @Test
    fun openListPullRequest() {
        pullRequestPresenter.openListPullRequest(pullRequestListMock())
        verify(pullRequestView).showListPullRequest(pullRequestListMock())
    }

    @Test
    fun errorShowPullRequestCard() {
        pullRequestPresenter.errorShowPullRequestCard(errorMessagePullRequestMock())
        verify(pullRequestView).showErrorPullRequestCard(errorMessagePullRequestMock())
    }
}