package com.dobler.desafio_android.data.repository.githubRepository

import androidx.annotation.MainThread
import androidx.lifecycle.Transformations
import androidx.paging.toLiveData
import com.dobler.desafio_android.data.model.GithubRepository
import com.dobler.desafio_android.util.paging.Listing
import io.reactivex.Observable
import java.util.concurrent.Executors

class Repository(private val sourceFactory: GithubRepositoryDataSource) :
    RepositoryContract {

    private val networkExecutor = Executors.newFixedThreadPool(5)

    @MainThread
    override fun getPage(): Observable<Listing<GithubRepository>> {
        val livePagedList = sourceFactory.toLiveData(
            pageSize = 30,
            fetchExecutor = networkExecutor
        )

        val refreshState = Transformations.switchMap(sourceFactory.sourceLiveData) {
            it.initialLoad
        }
        return Observable.just(
            Listing(
                pagedList = livePagedList,
                networkState = Transformations.switchMap(sourceFactory.sourceLiveData) {
                    it.networkState
                },
                retry = {
                    sourceFactory.sourceLiveData.value?.retryAllFailed()
                },
                refresh = {
                    sourceFactory.sourceLiveData.value?.invalidate()
                },
                refreshState = refreshState
            )
        )
    }
}