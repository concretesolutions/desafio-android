package br.com.desafio.concrete.view.pullrequest

import br.com.desafio.concrete.base.BasePresenter
import br.com.desafio.concrete.base.BaseView
import br.com.desafio.concrete.model.PullRequest
import br.com.desafio.concrete.model.Repository

/**
 * Created by Malkes on 24/09/2018.
 */
interface PullRequestContract {
    interface View : BaseView{
        fun onListItemClicked(pullRequest: PullRequest)
        fun onPullRequestLoaded(pullRequests: ArrayList<PullRequest>)
        fun showEmptyState()
    }

    interface Presenter : BasePresenter<View>{
        fun fetchPullRequests(repository: Repository)
    }
}