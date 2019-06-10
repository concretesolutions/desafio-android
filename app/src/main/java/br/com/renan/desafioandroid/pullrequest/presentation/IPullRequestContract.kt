package br.com.renan.desafioandroid.pullrequest.presentation

import br.com.renan.desafioandroid.model.data.PullRequestList

interface IPullRequestContract {

    interface View {
        fun pullRequestSuccess(pullRequestList: PullRequestList)
        fun showPullRequestLoading()
        fun showPullRequestError()
        fun showPullRequestSucess()
    }

    interface Presenter {
        fun bind(view: View)
        fun requestPullRequestData(login: String, repoName: String)
    }
}