package com.bassul.mel.app.feature.pullRequestsList.interactor

import com.bassul.mel.app.R
import com.bassul.mel.app.callback.RepositorySelectedRepositoriesCallback
import com.bassul.mel.app.domain.PullRequest
import com.bassul.mel.app.feature.pullRequestsList.PullRequestListContract
import com.bassul.mel.app.feature.pullRequestsList.repository.model.PullRequestListResponse

class PullRequestInteractorImpl(
    val presenter: PullRequestListContract.Presenter,
    val repository: PullRequestListContract.Repository
) : PullRequestListContract.Interactor  {


     override fun getSelectedItem(login: String, nameRepository: String) {
        repository.readPullRequestJson(login, nameRepository, object : RepositorySelectedRepositoriesCallback{
            override fun onSuccess(pullRequestList: List<PullRequestListResponse>) {
                presenter.openListPullRequest(convertPullRequestListResponseToPullResponse(pullRequestList))
            }

            override fun onError(s: String) {
                presenter.errorShowPullRequestCard(R.string.error_pull_request)
            }

        })
    }

    override fun convertPullRequestListResponseToPullResponse(pullRequestList: List<PullRequestListResponse>) : ArrayList<PullRequest>{
        val pullRequests : ArrayList<PullRequest> = arrayListOf()
        pullRequestList.forEach{
            val pr = PullRequest(it.html_url,
                it.updated_at,
                it.body,
                it.user.login,
                it.user.avatar_url)
            pullRequests.add(pr)
        }
        return pullRequests
    }


}