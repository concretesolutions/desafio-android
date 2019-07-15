package com.pedrenrique.githubapp.core.domain

import com.pedrenrique.githubapp.core.data.PaginatedData
import com.pedrenrique.githubapp.core.data.Repository
import com.pedrenrique.githubapp.core.data.datasource.RepositoryDataSource

class LoadMoreRepositories(
    private val dataSource: RepositoryDataSource
) : UseCase<LoadMoreRepositories.Params, PaginatedData<Repository>>() {

    override suspend fun run(params: Params) =
        dataSource.loadMore(params.lastPage)

    data class Params(val lastPage: Int)
}