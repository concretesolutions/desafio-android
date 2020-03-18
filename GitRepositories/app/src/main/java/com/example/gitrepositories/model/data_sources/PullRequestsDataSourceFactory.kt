package com.example.gitrepositories.model.data_sources

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.gitrepositories.model.dto.PullRequest
import com.example.gitrepositories.model.services.GitHubService
import io.reactivex.disposables.CompositeDisposable

class PullRequestsDataSourceFactory(private val compositeDisposable: CompositeDisposable, private val gitHubService: GitHubService)
    : DataSource.Factory<Int, PullRequest>() {

    override fun create(): DataSource<Int, PullRequest> {
        return PullRequestsDataSource(gitHubService, compositeDisposable)
    }
}