package br.com.guilherme.solution.ui.pullRequests

import br.com.guilherme.solution.api.ApiInterface
import br.com.guilherme.solution.models.PullRequest
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PullRequestsPresenter : PullRequestsContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private lateinit var view: PullRequestsContract.View
    private val api: ApiInterface = ApiInterface.create()

    override fun loadData(owner: String, repo: String) {
        val subscribe = api.getPullRequests(owner, repo).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ pullRequests: List<PullRequest>? ->
                view.loadDataSuccess(pullRequests!!)
            }, { error ->
                view.showErrorMessage(error.localizedMessage)
            })

        subscriptions.add(subscribe)
    }

    override fun subscribe() {
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: PullRequestsContract.View) {
        this.view = view
    }
}