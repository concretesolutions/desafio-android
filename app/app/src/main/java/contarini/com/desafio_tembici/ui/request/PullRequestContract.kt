package contarini.com.desafio_tembici.ui.request

import contarini.com.desafio_tembici.data.models.PullRequestResponse
import contarini.com.desafio_tembici.ui.base.BasePresenter
import contarini.com.desafio_tembici.ui.base.BaseView

interface PullRequestContract {

    interface View : BaseView<Presenter> {
        fun displayError(msg: String?)
        fun displayPullRequests(requests : List<PullRequestResponse>, open : Int, closed : Int)
        fun showLoading(loading : Boolean)

    }

    interface Presenter : BasePresenter<View> {
        fun getPullRequest(creator : String, repository : String)
        fun setPullRequests(requests : List<PullRequestResponse>, open : Int, closed : Int)
        fun setError(error : Throwable)
    }

    interface Model {
        fun loadPullRequest(creator : String, repository : String)
    }
}