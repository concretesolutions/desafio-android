package br.com.renan.desafioandroid

import br.com.renan.desafioandroid.model.data.PullRequestList

interface IPullRequestContract {

    interface View {
        fun pullRequestSuccess(pullRequestList: PullRequestList)
        fun showPullRequestLoading()
        fun showPullRequestError()
        fun showPullRequestSucess()
    }

    interface Presenter {
        fun bind(view: IPullRequestContract.View)
        fun requestPullRequestData(login: String, repoName: String)
    }
}