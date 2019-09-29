package br.com.guilherme.solution.ui.repositoryList

import br.com.guilherme.solution.api.ApiInterface
import br.com.guilherme.solution.models.Response
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RepositoryListPresenter : RepositoryListContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private lateinit var view: RepositoryListContract.View
    private val api: ApiInterface = ApiInterface.create()

    override fun loadData(q: String, sort: String, page: Int) {
        val subscribe = api.getRepositories(q, sort, page).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response: Response? ->
                view.loadDataSuccess(response!!)
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

    override fun attach(view: RepositoryListContract.View) {
        this.view = view
    }
}