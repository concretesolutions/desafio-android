package com.pedrenrique.githubapp.core.domain

import com.pedrenrique.githubapp.core.data.PaginatedData
import com.pedrenrique.githubapp.core.data.PullRequest
import com.pedrenrique.githubapp.core.data.Repository
import com.pedrenrique.githubapp.core.data.datasource.RepositoryDataSource

class LoadMorePRFromRepository(
    private val dataSource: RepositoryDataSource
) : UseCase<LoadMorePRFromRepository.Params, PaginatedData<PullRequest>>() {

    override suspend fun run(params: Params) =
        dataSource.listMorePR(params.lastPage, params.repository)

    data class Params(val lastPage: Int, val repository: Repository)
}