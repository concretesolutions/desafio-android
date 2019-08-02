package wilquer.lima.desafioconcrete.ui.pullrequest

import wilquer.lima.desafioconcrete.data.model.PullRequest

class PullRequestContract {
    interface View {
        fun setProgress(active: Boolean)

        fun pullrequests(listPr: List<PullRequest>?)

        fun error(msg: String)
    }

    interface Presenter {
        fun getPullRequests(creator: String, repo: String)
    }
}