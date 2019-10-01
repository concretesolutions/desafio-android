package contarini.com.desafio_android.ui.request

import contarini.com.desafio_android.data.models.PullRequestResponse


class PullRequestPresenter : PullRequestContract.Presenter {

    private var mView: PullRequestContract.View? = null
    private var mModel: PullRequestContract.Model = PullRequestModel(this)

    override fun getPullRequest(creator: String, repository: String) {
        mView?.showLoading(true)
        mModel.loadPullRequest(creator, repository)
    }

    override fun setPullRequests(requests : List<PullRequestResponse>, open : Int, closed : Int) {
        mView?.showLoading(false)
        mView?.displayPullRequests(requests, open, closed)
    }

    override fun setError(error: Throwable) {
        mView?.displayError(error.message)
        mView?.showLoading(false)
    }

    override fun attachView(mvpView: PullRequestContract.View?) {
        mView = mvpView
    }

    override fun detachView() {
        mView = null
    }

    override fun onItemClick(request: PullRequestResponse) {
        mView?.startWebRequest(request)
    }

}