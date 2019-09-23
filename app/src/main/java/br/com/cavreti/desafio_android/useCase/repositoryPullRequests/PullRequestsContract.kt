package br.com.cavreti.desafio_android.useCase.repositoryPullRequests

import br.com.cavreti.desafio_android.data.PullRequest

interface PullRequestsContract {

    interface View {
        fun loadPullRequests(pullRequests: List<PullRequest>)
        fun loadPullRequestsFailed()

    }

    interface Presenter {
        fun getPullRequests(ownerName: String, repoName: String, page: Int)
    }
}