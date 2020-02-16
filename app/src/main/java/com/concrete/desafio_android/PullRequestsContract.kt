package com.concrete.desafio_android

import com.concrete.desafio_android.domain.PullRequest

interface PullRequestsContract {

    interface View{
        fun showList(pullReqs: ArrayList<PullRequest>)
        fun showFailureMessage(message: String)
        fun showErrorMessage(message: String)
    }

    interface Presenter{
        fun getPullRequests(creator: String, repositoryString: String)
    }

    interface Callback{
        fun onSucces(repositories: ArrayList<PullRequest>)
        fun onError()
        fun onFailure(message: String)
    }
}