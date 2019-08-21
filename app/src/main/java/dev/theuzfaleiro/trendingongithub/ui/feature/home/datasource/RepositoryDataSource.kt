package dev.theuzfaleiro.trendingongithub.ui.feature.home.datasource

import androidx.paging.PageKeyedDataSource
import dev.theuzfaleiro.trendingongithub.network.GitHubEndpoint
import dev.theuzfaleiro.trendingongithub.ui.feature.home.model.data.Repository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

class RepositoryDataSource(private val gitHubEndpoint: GitHubEndpoint) :
    PageKeyedDataSource<Int, Repository>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        loadInitialCallback: LoadInitialCallback<Int, Repository>
    ) {
        gitHubEndpoint.fetchRepositoriesFromApi(page = 1)
            .map {
                it.repositories.map { repository ->
                    Repository(repository)
                }.toList()
            }.subscribeBy(
                onSuccess = {
                    loadInitialCallback.onResult(it, 1, 2)
                },
                onError = {
                }).addTo(CompositeDisposable())
    }

    override fun loadAfter(params: LoadParams<Int>, loadCallback: LoadCallback<Int, Repository>) {
        gitHubEndpoint.fetchRepositoriesFromApi(page = params.key)
            .map {
                it.repositories.map { repository ->
                    Repository(repository)
                }.toList()
            }.subscribeBy(onSuccess = {
                loadCallback.onResult(it, params.key + 1)
            }, onError = {
                TODO("Handle Error For Loading Repositories")
            }).addTo(CompositeDisposable())
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Repository>) {}
}