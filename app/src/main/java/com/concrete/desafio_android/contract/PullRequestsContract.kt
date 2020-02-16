package com.concrete.desafio_android.contract

import com.concrete.desafio_android.data.domain.PullRequest

interface PullRequestsContract {

    interface View {
        fun showList(pullReqs: ArrayList<PullRequest>)
        fun showErrorMessage(messageId: Int)
    }

    interface Presenter {
        fun getPullRequests(creator: String, repositoryString: String)
        fun makeTitle(name: String): String
    }

    interface Callback {
        fun onSuccess(repositories: ArrayList<PullRequest>)
        fun onError(messageId: Int)
    }

    interface Api {
        fun listPullRequests(creator: String, repository: String)
    }
}