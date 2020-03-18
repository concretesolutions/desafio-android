package com.example.gitrepositories.model.data_sources

import androidx.paging.PageKeyedDataSource
import com.example.gitrepositories.model.dto.PullRequest
import com.example.gitrepositories.model.services.GitHubService
import io.reactivex.disposables.CompositeDisposable
import java.util.*

class PullRequestsDataSource(private val gitHubService: GitHubService, private val compositeDisposable: CompositeDisposable)
    : PageKeyedDataSource<Int, PullRequest>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, PullRequest>) {
//        compositeDisposable.add(
//            gitHubService.getPullRequests(1, params.requestedLoadSize)
//                .subscribe { response ->
//                    callback.onResult(response.pullRequests, null, 2)
//                }
//        )
        callback.onResult(listOf(), null, 2)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, PullRequest>) {
//        compositeDisposable.add(
//            gitHubService.getPullRequests(params.key, params.requestedLoadSize)
//                .subscribe { response ->
//                    callback.onResult(response.pullRequests, params.key + 1)
//                }
//        )
        callback.onResult(listOf(), params.key + 1)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, PullRequest>) {}
}