package com.diegoferreiracaetano.domain.repo.interactor

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.diegoferreiracaetano.domain.Constants
import com.diegoferreiracaetano.domain.NetworkState
import com.diegoferreiracaetano.domain.repo.Repo
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.subscribers.DisposableSubscriber

class CallbackRepoInteractor(private val saveInicialCacheInteractor: SaveRepoInicialInteractor,
                             private val saveCacheInteractor: SaveRepoPageInteractor): PagedList.BoundaryCallback<Repo>() {

    private var disposable = CompositeDisposable()
    private var retryCompletable: Completable? = null
    val initialLoad = MutableLiveData<NetworkState>()
    val networkState = MutableLiveData<NetworkState>()

    override fun onZeroItemsLoaded() {
        disposable.add(saveInicialCacheInteractor.execute(SaveRepoInicialInteractor.Request(1))
                .subscribeWith(object : DisposableSubscriber<List<Long>>(){
                    override fun onStart() {
                        super.onStart()
                        initialLoad.postValue(NetworkState.LOADING)
                    }

                    override fun onNext(t: List<Long>) {
                        if(t.isEmpty()){
                            initialLoad.postValue(NetworkState.IS_EMPTY)
                        }else{
                            initialLoad.postValue(NetworkState.LOADED)
                        }
                    }

                    override fun onError(t: Throwable) {
                        val erro = NetworkState.error(t.message)
                        initialLoad.postValue(erro)
                        networkState.postValue(erro)
                        setRetry(Action { onZeroItemsLoaded() })
                    }

                    override fun onComplete() {
                        networkState.postValue(NetworkState.LOADED)
                    }
                }))
    }

    override fun onItemAtEndLoaded(repo: Repo) {
        disposable.add(saveCacheInteractor.execute(SaveRepoPageInteractor.Request(Constants.PAGE_SIZE))
                .subscribeWith(object : DisposableSubscriber<List<Long>>() {
                    override fun onStart() {
                        super.onStart()
                        networkState.postValue(NetworkState.LOADING)
                    }

                    override fun onNext(t: List<Long>) {

                    }

                    override fun onError(t: Throwable) {
                        val erro = NetworkState.error(t.message)
                        networkState.postValue(erro)
                        setRetry(Action { onItemAtEndLoaded(repo) })
                    }

                    override fun onComplete() {
                        networkState.postValue(NetworkState.LOADED)
                    }
                }))
    }

    fun retry() {
        if (retryCompletable != null) {
            disposable.add(retryCompletable!!
                    .subscribe({ }))
        }
    }

    private fun setRetry(action: Action?) {
        if (action == null) {
            this.retryCompletable = null
        } else {
            this.retryCompletable = Completable.fromAction(action)
        }
    }

    fun clear(){
        disposable.clear()
    }
}