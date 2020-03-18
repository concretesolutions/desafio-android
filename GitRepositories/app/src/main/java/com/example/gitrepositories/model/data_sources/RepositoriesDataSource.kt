package com.example.gitrepositories.model.data_sources

import androidx.paging.PageKeyedDataSource
import com.example.gitrepositories.model.dto.Repository
import com.example.gitrepositories.model.services.GitHubService
import io.reactivex.disposables.CompositeDisposable

class RepositoriesDataSource(private val gitHubService: GitHubService, private val compositeDisposable: CompositeDisposable)
    : PageKeyedDataSource<Int, Repository>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Repository>) {
//        compositeDisposable.add(
//            gitHubService.getRepositories(1, params.requestedLoadSize)
//                .subscribe { response ->
//                    callback.onResult(response.repositories, null, 2)
//                }
//        )
        callback.onResult(listOf(Repository("Rounded Image", "This is a project to make the ImageView rounded, wether setting it on xml or programatically", "markMc", "Mark McFetter", 30, 3, null)), null, 2)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Repository>) {
//        compositeDisposable.add(
//            gitHubService.getRepositories(params.key, params.requestedLoadSize)
//                .subscribe { response ->
//                    callback.onResult(response.repositories, params.key + 1)
//                }
//        )
        callback.onResult(listOf(), params.key + 1)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Repository>) {}
}