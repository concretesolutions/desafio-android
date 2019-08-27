package com.joaodomingos.desafio_android.ui.data_source

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.joaodomingos.desafio_android.api.NetworkService
import com.joaodomingos.desafio_android.api.State
import com.joaodomingos.desafio_android.models.PullRequestModel
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers

//class PullRequestDataSource(
//    private val networkService: NetworkService,
//    private val compositeDisposable: CompositeDisposable,
//    private var user: String,
//    private var repo: String)
//    : PageKeyedDataSource<Int, PullRequestModel>() {
//
//    var state: MutableLiveData<State> = MutableLiveData()
//    private var retryCompletable: Completable? = null
//
//    override fun loadInitial(params: PageKeyedDataSource.LoadInitialParams<Int>, callback: PageKeyedDataSource.LoadInitialCallback<Int, PullRequestModel>) {
//        updateState(State.LOADING)
//        compositeDisposable.add(
//            networkService.getPullRequestListItens(1, user, repo)
//                .subscribe(
//                    { response ->
//                        updateState(State.DONE)
//                        callback.onResult(response,
//                            null,
//                            2
//                        )
//                    },
//                    {
//                        updateState(State.ERROR)
//                        setRetry(Action { loadInitial(params, callback) })
//                    }
//                )
//        )
//    }
//
//    override fun loadAfter(params: PageKeyedDataSource.LoadParams<Int>, callback: PageKeyedDataSource.LoadCallback<Int, PullRequestModel>) {
//        updateState(State.LOADING)
//        compositeDisposable.add(
//            networkService.getPullRequestListItens(params.key, user, repo)
//                .subscribe(
//                    { response ->
//                        updateState(State.DONE)
//                        callback.onResult(response,
//                            params.key + 1
//                        )
//                    },
//                    {
//                        updateState(State.ERROR)
//                        setRetry(Action { loadAfter(params, callback) })
//                    }
//                )
//        )
//    }
//
//    override fun loadBefore(params: PageKeyedDataSource.LoadParams<Int>, callback: PageKeyedDataSource.LoadCallback<Int, PullRequestModel>) {
//    }
//
//    private fun updateState(state: State) {
//        this.state.postValue(state)
//    }
//
//    fun retry() {
//        if (retryCompletable != null) {
//            compositeDisposable.add(retryCompletable!!
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe())
//        }
//    }
//
//    private fun setRetry(action: Action?) {
//        retryCompletable = if (action == null) null else Completable.fromAction(action)
//    }

//}