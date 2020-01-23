package br.com.rmso.popularrepositories.ui.pullrequest

import br.com.rmso.popularrepositories.model.PullRequest

interface IPullRequest {

    interface View {
        fun progressBar(status: Boolean)
        fun updateList (list: List<PullRequest>?, owner: String, repositoryName: String)
        fun errorRequest (message: String)
    }

    interface Presenter{
        fun requestList(list: List<PullRequest>?, owner: String, repositoryName: String)
    }
}