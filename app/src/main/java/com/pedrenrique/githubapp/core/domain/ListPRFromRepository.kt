package com.pedrenrique.githubapp.core.domain

import com.pedrenrique.githubapp.core.data.PaginatedData
import com.pedrenrique.githubapp.core.data.PullRequest
import com.pedrenrique.githubapp.core.data.Repository
import com.pedrenrique.githubapp.core.data.datasource.RepositoryDataSource

class ListPRFromRepository(
    private val dataSource: RepositoryDataSource
) : UseCase<ListPRFromRepository.Params, PaginatedData<PullRequest>>() {
    override suspend fun run(params: Params) =
        dataSource.listPR(params.repository)

    data class Params(val repository: Repository)
}