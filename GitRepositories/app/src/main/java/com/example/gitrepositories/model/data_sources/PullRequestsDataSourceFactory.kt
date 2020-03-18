package com.example.gitrepositories.model.data_sources

import androidx.paging.DataSource
import com.example.gitrepositories.model.dto.PullRequest

class PullRequestsDataSourceFactory(private val initialFetchCompletedCallback: (Boolean) -> Unit, private val errorCallback: () -> Unit, private val repoCreator: String, private val repoName: String) : DataSource.Factory<Int, PullRequest>() {

    override fun create(): DataSource<Int, PullRequest> {
        return PullRequestsDataSource(initialFetchCompletedCallback, errorCallback, repoCreator, repoName)
    }
}