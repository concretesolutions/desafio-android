package dev.theuzfaleiro.trendingongithub.ui.feature.home.repository

import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import dev.theuzfaleiro.trendingongithub.ui.feature.home.datasource.RepositoryDataSourceFactory
import dev.theuzfaleiro.trendingongithub.ui.feature.home.model.data.Repository
import io.reactivex.Observable

class HomeRepository(private val repositoryDataSourceFactory: RepositoryDataSourceFactory) {
    fun fetchTrendingRepositories(): Observable<PagedList<Repository>> =
        RxPagedListBuilder(repositoryDataSourceFactory, 30)
            .buildObservable()
}