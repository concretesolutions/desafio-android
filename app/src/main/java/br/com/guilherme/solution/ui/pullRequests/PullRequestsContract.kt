package br.com.guilherme.solution.ui.pullRequests

import br.com.guilherme.solution.models.PullRequest
import br.com.guilherme.solution.ui.base.Base

class PullRequestsContract {

    interface View : Base.View {
        fun showErrorMessage(error: String)
        fun loadDataSuccess(pullRequests: List<PullRequest>)
    }

    interface Presenter : Base.Presenter<View> {
        fun loadData(owner: String, repo: String)
    }
}