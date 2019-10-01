package contarini.com.desafio_android.ui.request

import contarini.com.desafio_android.data.models.PullRequestResponse
import contarini.com.desafio_android.ui.base.BasePresenter
import contarini.com.desafio_android.ui.base.BaseView


interface PullRequestContract {

    interface View : BaseView<Presenter> {
        fun displayError(msg: String?)
        fun displayPullRequests(requests : List<PullRequestResponse>, open : Int, closed : Int)
        fun showLoading(loading : Boolean)
        fun startWebRequest(requests: PullRequestResponse)
    }

    interface Presenter : BasePresenter<View> {
        fun getPullRequest(creator : String, repository : String)
        fun setPullRequests(requests : List<PullRequestResponse>, open : Int, closed : Int)
        fun setError(error : Throwable)
        fun onItemClick(request : PullRequestResponse)
    }

    interface Model {
        fun loadPullRequest(creator : String, repository : String)
    }
}