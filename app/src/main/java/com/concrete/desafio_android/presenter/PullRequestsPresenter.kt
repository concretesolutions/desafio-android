package com.concrete.desafio_android.presenter

import com.concrete.desafio_android.contract.PullRequestsContract
import com.concrete.desafio_android.data.external.GithubRepository
import com.concrete.desafio_android.domain.PullRequest

class PullRequestsPresenter(
    private val view: PullRequestsContract.View
) : PullRequestsContract.Presenter,
    PullRequestsContract.Callback {

    private val repository =
        GithubRepository(
            pullRequestsCallback = this
        )

    override fun getPullRequests(creator: String, repositoryString: String) {
        repository.listPullRequests(creator, repositoryString)
    }

    override fun onSuccess(repositories: ArrayList<PullRequest>) {
        view.showList(repositories)
    }

    override fun makeTitle(name: String): String {
        return "${name}'s Pull Requests"
    }

    override fun onError(messageId: Int) {
        view.showErrorMessage(messageId)
    }
}