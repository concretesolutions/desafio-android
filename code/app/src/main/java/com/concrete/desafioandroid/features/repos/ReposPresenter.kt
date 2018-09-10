package com.concrete.desafioandroid.features.repos

import com.concrete.desafioandroid.data.models.OwnerDetails
import com.concrete.desafioandroid.data.models.Repo
import com.concrete.desafioandroid.features.base.BasePresenter
import com.concrete.desafioandroid.utils.*

class ReposPresenter(private val interactor: ReposInteractor): BasePresenter<ReposView>() {

    var page = 1
    var showLoadAtFirstUse = true

    override fun unsubscribe() {
        compositeDisposable.clear()
    }

    fun getRepos(forceLoad: Boolean) {
        if (forceLoad) {
            unsubscribe()

            view?.loading(true)
            view?.showProgress(showLoadAtFirstUse)
            val disposable = interactor.getReposList(assembleQueries()) {
                view?.onGetReposListSuccess(it)
                fetchDetail(it)
                view?.loading(false)
                showLoadAtFirstUse = false
                view?.showProgress(showLoadAtFirstUse)
            }

            compositeDisposable.add(disposable)
        }
    }

    private fun fetchDetail(list: List<Repo>) {
        var index = 0
        for (repo in list) {
            val disposable = interactor.fetchOwnerDetails(repo.ownerOverview.url) {
                repo.ownerDetails = it
                view?.onFetchDetails(index++)
            }

            compositeDisposable.add(disposable)
        }
    }

    private fun assembleQueries(): HashMap<String, String> {
        val queries = HashMap<String, String>()
        queries.put(QUERY_LANGUAGE_KEY, QUERY_LANGUAGE_JAVA_VALUE)
        queries.put(QUERY_SORT_KEY, QUERY_SORT_START_VALUE)
        queries.put(QUERY_PAGE_KEY, (page).toString())

        return queries
    }

    fun onRestoreInstance(currentPage: Int) {
        page = currentPage
    }

}