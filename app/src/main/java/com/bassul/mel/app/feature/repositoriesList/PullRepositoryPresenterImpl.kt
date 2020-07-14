package com.bassul.mel.app.feature.repositoriesList

import com.bassul.mel.app.domain.PullRequest
import kotlin.collections.ArrayList

class PullRepositoryPresenterImpl (val view: PullRequestListContract.View) :PullRequestListContract.Presenter {


    override fun openListPullRequest(pullRequest: ArrayList<PullRequest>) {
        return view.showListPullRequest(pullRequest)
    }


    override fun errorShowPullRequestCard(errorPullRequest: Int) {
        return view.showErrorPullRequestCard(errorPullRequest)
    }

}