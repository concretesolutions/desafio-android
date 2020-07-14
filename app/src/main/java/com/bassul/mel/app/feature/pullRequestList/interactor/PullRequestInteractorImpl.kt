package com.bassul.mel.app.feature.pullRequestList.interactor

import android.util.Log
import com.bassul.mel.app.R
import com.bassul.mel.app.callback.RepositotySelectedRepositoriesCallback
import com.bassul.mel.app.domain.PullRequest
import com.bassul.mel.app.feature.repositoriesList.PullRequestListContract
import com.bassul.mel.app.feature.pullRequestList.repository.model.PullRequestListResponse

class PullRequestInteractorImpl(
    val presenter: PullRequestListContract.Presenter,
    val repository: PullRequestListContract.Repository
) : PullRequestListContract.Interactor  {


     override fun getSelectedItem(login: String, nameRepository: String) {
        repository.readPullRequestJson(login, nameRepository, object : RepositotySelectedRepositoriesCallback{
            override fun onSuccess(pullRequestList: List<PullRequestListResponse>) {
                Log.i("MELLINA getSelectedItem", "  Pull Request List:  "+pullRequestList)
                presenter.openListPullRequest(convertPullRequestListResponseToPullResponse(pullRequestList))
            }

            override fun onError(s: String) {
                presenter.errorShowPullRequestCard(R.string.error_pull_request)
            }

        })
    }

    private fun convertPullRequestListResponseToPullResponse(pullRequestList: List<PullRequestListResponse>) : ArrayList<PullRequest>{
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