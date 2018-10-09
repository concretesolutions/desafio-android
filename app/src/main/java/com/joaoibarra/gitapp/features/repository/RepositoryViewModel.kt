package com.joaoibarra.gitapp.features.repository

import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import android.arch.paging.RxPagedListBuilder
import com.joaoibarra.gitapp.api.GitApiService
import com.joaoibarra.gitapp.api.model.Repo
import com.joaoibarra.gitapp.api.paging.RepositoryDatasourceFactory
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RepositoryViewModel : ViewModel() {
    var repositories: Observable<PagedList<Repo>>

    private val compositeDisposable = CompositeDisposable()

    private val repositoryFactory: RepositoryDatasourceFactory

    private val pageSize = 30

    init {
        repositoryFactory = RepositoryDatasourceFactory(compositeDisposable, GitApiService.create())

        val config = PagedList.Config.Builder()
                .setPageSize(pageSize)
                .setInitialLoadSizeHint(pageSize)
                .setPrefetchDistance(10)
                .setEnablePlaceholders(false)
                .build()

        repositories = RxPagedListBuilder(repositoryFactory, config)
                .setFetchScheduler(Schedulers.io())
                .buildObservable()
                .cache()

    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
