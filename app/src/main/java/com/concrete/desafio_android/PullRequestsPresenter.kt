package com.concrete.desafio_android

import com.concrete.desafio_android.domain.PullRequest

class PullRequestsPresenter(
    private val view: PullRequestsContract.View
): PullRequestsContract.Presenter, PullRequestsContract.Callback {

    private val repository = GithubRepository(pullRequestsCallback = this)

    override fun getPullRequests(creator: String, repositoryString: String) {
        repository.listPullRequests(creator, repositoryString)
    }

    override fun onSucces(repositories: ArrayList<PullRequest>) {
        view.showList(repositories)
    }

    override fun onError() {
//        view.showErrorMessage("")
    }

    override fun onFailure(message: String) {
        view.showFailureMessage(message)
    }
}