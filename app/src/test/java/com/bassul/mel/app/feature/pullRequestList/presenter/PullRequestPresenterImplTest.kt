package com.bassul.mel.app.feature.pullRequestList.presenter


import com.bassul.mel.app.feature.pullRequestsList.PullRequestListContract
import com.bassul.mel.app.feature.pullRequestList.PullRequestMock.Companion.errorMessagePullRequestMock
import com.bassul.mel.app.feature.pullRequestList.PullRequestMock.Companion.pullRequestListMock
import com.bassul.mel.app.feature.pullRequestsList.presenter.PullRequestPresenterImpl
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

internal class PullRequestPresenterImplTest {

    private lateinit var pullRequestPresenter: PullRequestListContract.Presenter

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
        verify(pullRequestView).showPullRequestList(pullRequestListMock())
    }

    @Test
    fun errorShowPullRequestCard() {
        pullRequestPresenter.errorShowPullRequest(errorMessagePullRequestMock())
        verify(pullRequestView).showErrorPullRequestList(errorMessagePullRequestMock())
    }
}