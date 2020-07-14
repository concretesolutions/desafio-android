package com.bassul.mel.app.feature.pullRequestsList.presenter

import com.bassul.mel.app.domain.PullRequest
import com.bassul.mel.app.feature.pullRequestsList.PullRequestListContract
import kotlin.collections.ArrayList

class PullRequestPresenterImpl (val view: PullRequestListContract.View) :
    PullRequestListContract.Presenter {


    override fun openListPullRequest(pullRequest: ArrayList<PullRequest>) =
        if(pullRequest.isEmpty()){
            view.showTextEmptyList()
        }else{
            view.showPullRequestList(pullRequest)
        }

    override fun errorShowPullRequestCard(errorPullRequest: Int) {
        return view.showErrorPullRequestList(errorPullRequest)
    }

}