package com.joaoibarra.gitapp.features.repository

import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import android.arch.paging.RxPagedListBuilder
import com.joaoibarra.gitapp.api.GitApiService
import com.joaoibarra.gitapp.api.model.Pull
import com.joaoibarra.gitapp.api.paging.PullDatasourceFactory
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PullViewModel(val user: String, val repo: String) : ViewModel() {

    var pulls: Observable<PagedList<Pull>>

    private val compositeDisposable = CompositeDisposable()

    private val pullFactory: PullDatasourceFactory

    private val pageSize = 30

    init {
        pullFactory = PullDatasourceFactory(compositeDisposable, GitApiService.create(), user, repo)

        val config = PagedList.Config.Builder()
                .setPageSize(pageSize)
                .setInitialLoadSizeHint(pageSize)
                .setPrefetchDistance(10)
                .setEnablePlaceholders(false)
                .build()

        pulls = RxPagedListBuilder(pullFactory, config)
                .setFetchScheduler(Schedulers.io())
                .buildObservable()
                .cache()

    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}
