package com.example.gitrepositories.model.data_sources

import androidx.paging.DataSource
import com.example.gitrepositories.model.dto.Repository

class RepositoriesDataSourceFactory(private val initialFetchCompletedCallback: (Boolean) -> Unit, private val errorCallback: () -> Unit) : DataSource.Factory<Int, Repository>() {

    override fun create(): DataSource<Int, Repository> {
        return RepositoriesDataSource(initialFetchCompletedCallback, errorCallback)
    }
}