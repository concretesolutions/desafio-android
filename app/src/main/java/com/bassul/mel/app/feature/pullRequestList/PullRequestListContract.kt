package com.bassul.mel.app.feature.pullRequestList

import androidx.annotation.StringRes
import com.bassul.mel.app.callback.RepositorySelectedRepositoriesCallback
import com.bassul.mel.app.domain.PullRequest
import com.bassul.mel.app.feature.pullRequestList.repository.model.PullRequestListResponse

interface PullRequestListContract{

    interface View{
        fun showListPullRequest(pullRequest: ArrayList<PullRequest>)
        fun showErrorPullRequestCard(@StringRes errorPullRequest: Int)
    }

    interface Presenter{
        fun openListPullRequest(pullRequest: ArrayList<PullRequest>)
        fun errorShowPullRequestCard(@StringRes errorPullRequest: Int)
    }

    interface Interactor{
        fun getSelectedItem(login: String, nameRepository: String)
        fun convertPullRequestListResponseToPullResponse(pullRequestList: List<PullRequestListResponse>): ArrayList<PullRequest>
    }

    interface Repository{
        fun readPullRequestJson(login : String, nameRepository : String, callback: RepositorySelectedRepositoriesCallback)
    }
}