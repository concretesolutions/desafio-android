package com.concrete.desafioandroid.features.pulls

import com.concrete.desafioandroid.features.base.BasePresenter


class PullsPresenter(private val interactor: PullsInteractor): BasePresenter<PullsView>() {

    override fun unsubscribe() {
        compositeDisposable.clear()
    }

    fun getPullsRequests(pullsUrl: String, forceUpdate: Boolean) {
        if (forceUpdate) {
            unsubscribe()

            view?.showProgress(true)
            val disposable = interactor.getPullsList(pullsUrl,
                    { list, openedIssues, closedIssues ->
                        view?.showProgress(false)
                        view?.onGetPullsRequests(list)
                        view?.updateUi(openedIssues, closedIssues)
                    },
                    {
                        view?.showProgress(false)
                        view?.showError(it)
                    })

            compositeDisposable.add(disposable)
        }
    }

}