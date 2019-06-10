package br.com.renan.desafioandroid.pullrequest.presentation

import br.com.renan.desafioandroid.model.data.PullRequest

interface IPullRequestContract {

    interface View {
        fun pullRequestSuccess(pullRequestList: List<PullRequest>)
        fun showPullRequestLoading()
        fun showPullRequestError()
        fun showPullRequestSucess()
        fun showPullRequestEmpty()
        fun showTotalPulls(pulls: List<PullRequest>)
    }

    interface Presenter {
        fun bind(view: View)
        fun requestPullRequestData(login: String, repoName: String)
    }
}