package com.bassul.mel.app.feature.pullRequestList.presenter

import com.bassul.mel.app.domain.PullRequest
import com.bassul.mel.app.feature.pullRequestList.PullRequestListContract
import kotlin.collections.ArrayList

class PullRequestPresenterImpl (val view: PullRequestListContract.View) :
    PullRequestListContract.Presenter {


    override fun openListPullRequest(pullRequest: ArrayList<PullRequest>) {
        return view.showListPullRequest(pullRequest)
    }


    override fun errorShowPullRequestCard(errorPullRequest: Int) {
        return view.showErrorPullRequestCard(errorPullRequest)
    }

}