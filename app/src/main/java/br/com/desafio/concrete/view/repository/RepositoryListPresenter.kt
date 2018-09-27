package br.com.desafio.concrete.view.repository

import br.com.desafio.concrete.base.ChallengeBasePresenter
import br.com.desafio.concrete.network.ApiDataSource
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * Created by Malkes on 24/09/2018.
 */
class RepositoryListPresenter(val api: ApiDataSource) :
        ChallengeBasePresenter<RepositoryListContract.View>(),
        RepositoryListContract.Presenter {


    private var page = 1
    private var query: String? = null

    val subscriberScheduler : Scheduler = Schedulers.io()
    val observerScheduler : Scheduler = AndroidSchedulers.mainThread()

    override fun fetchRepositories(query: String) {
        this.query = query

        view?.let { view ->

            view.showLoadingIndicator(true)

            api.fetchRepositories(query,1)
                    .subscribeOn(subscriberScheduler)
                    .observeOn(observerScheduler)
                    .doOnTerminate{
                        view.showLoadingIndicator(false)
                    }
                    .subscribe({ response ->
                        view.onRepositoriesLoaded(response.items)
                    }, { throwable ->
                        view.showUnexpectedError(throwable)
                    })
        }

    }

    override fun refreshList() {

        page = 1
        fetchRepositories(query!!)

    }
    override fun loadMore() {

        view?.let { view ->
            if(!query.isNullOrEmpty()){

                page++

                view.showPaginateLoading(true)

                api.fetchRepositories(query!!,page)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnTerminate{
                            view.showPaginateLoading(false)
                        }
                        .subscribe({ response ->
                            view.onLoadMoreComplete(response.items)
                        }, { _ ->
                            view.showPaginateError(true)
                        })
            }
        }
    }
}