package matheusuehara.github.contract

import matheusuehara.github.model.PullRequest

interface PullRequestContract {

    interface View {
        fun updatePullRequests(pullRequestResult: ArrayList<PullRequest>)
        fun updateStatus(status:String)
        fun showProgressBar()
        fun hideProgressBar()
        fun showNetworkError()
        fun showEmptyPullRequestMessage()
    }

    interface Presenter {
        fun getPullRequests(owner:String,repository:String,status:String)
        fun getPullRequestSuccess(pullRequestResult: ArrayList<PullRequest>?)
        fun getPullRequestError()
    }

}
