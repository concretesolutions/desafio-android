package com.pedrenrique.githubapp.core.domain

import com.pedrenrique.githubapp.core.data.PaginatedData
import com.pedrenrique.githubapp.core.data.Repository
import com.pedrenrique.githubapp.core.data.datasource.RepositoryDataSource

class ListRepositories(
    private val dataSource: RepositoryDataSource
) : UseCase.NoParam<PaginatedData<Repository>>() {
    override suspend fun run() =
        dataSource.list()
}