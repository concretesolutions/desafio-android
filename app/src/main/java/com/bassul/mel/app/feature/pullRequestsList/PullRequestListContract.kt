package com.bassul.mel.app.feature.pullRequestsList

import androidx.annotation.StringRes
import com.bassul.mel.app.callback.RepositorySelectedRepositoriesCallback
import com.bassul.mel.app.domain.PullRequest
import com.bassul.mel.app.feature.pullRequestsList.repository.model.PullRequestListResponse

interface PullRequestListContract{

    interface View{
        fun showPullRequestList(pullRequest: ArrayList<PullRequest>)
        fun showErrorPullRequestList(@StringRes errorPullRequest: Int)
      //  fun setLoadingState(isLoading: Boolean)
        fun showTextEmptyList()
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