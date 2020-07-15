package com.bassul.mel.app.feature.pullRequestsList.interactor

import com.bassul.mel.app.R
import com.bassul.mel.app.callback.RepositorySelectedRepositoriesCallback
import com.bassul.mel.app.feature.convertResponseToObject.Companion.convertPullRequestListResponseToPullResponse
import com.bassul.mel.app.feature.pullRequestsList.PullRequestListContract
import com.bassul.mel.app.feature.pullRequestsList.repository.model.PullRequestListResponse

class PullRequestInteractorImpl(
    val presenter: PullRequestListContract.Presenter,
    val repository: PullRequestListContract.Repository
) : PullRequestListContract.Interactor {

    override fun getSelectedItem(login: String, nameRepository: String) {
        repository.readPullRequestJson(
            login,
            nameRepository,
            object : RepositorySelectedRepositoriesCallback {
                override fun onSuccess(pullRequestList: List<PullRequestListResponse>) {
                    presenter.openListPullRequest(
                        convertPullRequestListResponseToPullResponse(
                            pullRequestList
                        )
                    )
                }

                override fun onError(s: String) {
                    presenter.errorShowPullRequestCard(R.string.error_pull_request)
                }
            })
    }



}