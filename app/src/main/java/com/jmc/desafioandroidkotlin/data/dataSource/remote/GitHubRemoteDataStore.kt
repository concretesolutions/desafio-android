package com.jmc.desafioandroidkotlin.data.dataSource.remote

import com.jmc.desafioandroidkotlin.data.dataSource.GitHubDataStore
import com.jmc.desafioandroidkotlin.domain.model.PullModel
import com.jmc.desafioandroidkotlin.domain.model.RepositoryModel
import com.jmc.desafioandroidkotlin.utils.await
import com.jmc.desafioandroidkotlin.utils.toPull
import com.jmc.desafioandroidkotlin.utils.toRepositorySearchResult


open class GitHubRemoteDataStore(
    private val gitHubApi: GitHubApi
) : GitHubDataStore {
    override suspend fun getRepositories(page: Int) =
        gitHubApi.getRepositories(page = page).await()!!.toRepositorySearchResult(page)

    override suspend fun getPulls(repository: String) =
        gitHubApi.getPulls(repository).await()!!.map { it.toPull() }

    override suspend fun saveRepositories(repositoryModels: List<RepositoryModel>) =
        throw UnsupportedOperationException()

    override suspend fun savePulls(repository: String, pullModels: List<PullModel>) =
        throw UnsupportedOperationException()
}