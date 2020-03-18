package com.example.gitrepositories.model.data_sources

import androidx.paging.DataSource
import com.example.gitrepositories.model.dto.Repository
import com.example.gitrepositories.model.services.GitHubService
import io.reactivex.disposables.CompositeDisposable

class RepositoriesDataSourceFactory(private val compositeDisposable: CompositeDisposable, private val gitHubService: GitHubService, private val initialFetchCompletedListener: (Boolean) -> Unit)
    : DataSource.Factory<Int, Repository>() {

    override fun create(): DataSource<Int, Repository> {
        return RepositoriesDataSource(gitHubService, compositeDisposable, initialFetchCompletedListener)
    }
}