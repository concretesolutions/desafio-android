package com.bassul.mel.app.feature.repositoriesList

import androidx.annotation.StringRes
import com.bassul.mel.app.domain.Item
import com.bassul.mel.app.callback.RepositotyAllRepositoriesCallback
import com.bassul.mel.app.callback.RepositotySelectedRepositoriesCallback
import com.bassul.mel.app.domain.PullRequest

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
    }

    interface Repository{
        fun readPullRequestJson(login : String, nameRepository : String, callback: RepositotySelectedRepositoriesCallback)
    }
}