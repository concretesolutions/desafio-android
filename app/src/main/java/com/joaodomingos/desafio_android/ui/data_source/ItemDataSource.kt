package com.joaodomingos.desafio_android.ui.data_source

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.joaodomingos.desafio_android.api.NetworkService
import com.joaodomingos.desafio_android.api.State
import com.joaodomingos.desafio_android.models.SearchItensModel
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers

class ItemDataSource (
    private val networkService: NetworkService,
    private val compositeDisposable: CompositeDisposable)
    : PageKeyedDataSource<Int, SearchItensModel>() {

    var state: MutableLiveData<State> = MutableLiveData()
    private var retryCompletable: Completable? = null

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, SearchItensModel>) {
        updateState(State.LOADING)
        compositeDisposable.add(
            networkService.getNews(1)
                .subscribe(
                    { response ->
                        updateState(State.DONE)
                        callback.onResult(response.itemsList,
                            null,
                            2
                        )
                    },
                    {
                        updateState(State.ERROR)
                        setRetry(Action { loadInitial(params, callback) })
                    }
                )
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, SearchItensModel>) {
        updateState(State.LOADING)
        compositeDisposable.add(
            networkService.getNews(params.key)
                .subscribe(
                    { response ->
                        updateState(State.DONE)
                        callback.onResult(response.itemsList,
                            params.key + 1
                        )
                    },
                    {
                        updateState(State.ERROR)
                        setRetry(Action { loadAfter(params, callback) })
                    }
                )
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, SearchItensModel>) {
    }

    private fun updateState(state: State) {
        this.state.postValue(state)
    }

    fun retry() {
        if (retryCompletable != null) {
            compositeDisposable.add(retryCompletable!!
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
        }
    }

    private fun setRetry(action: Action?) {
        retryCompletable = if (action == null) null else Completable.fromAction(action)
    }

}