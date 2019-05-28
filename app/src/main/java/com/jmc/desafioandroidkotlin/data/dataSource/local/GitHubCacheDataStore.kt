package com.jmc.desafioandroidkotlin.data.dataSource.local

import com.jmc.desafioandroidkotlin.data.dataSource.GitHubDataStore
import com.jmc.desafioandroidkotlin.domain.model.PullModel
import com.jmc.desafioandroidkotlin.domain.model.RepositoryModel
import com.jmc.desafioandroidkotlin.utils.toPull
import com.jmc.desafioandroidkotlin.utils.toPullEntity
import com.jmc.desafioandroidkotlin.utils.toRepositoryEntity


open class GitHubCacheDataStore(
    private val gitHubDatabase: GitHubDatabase
) : GitHubDataStore {
    override suspend fun getRepositories(page: Int) =
        throw UnsupportedOperationException()

    override suspend fun getPulls(repository: String): List<PullModel> {
        return gitHubDatabase.pulls
            .getPulls(repository)
            .map { it.toPull() }
    }

    override suspend fun saveRepositories(repositoryModels: List<RepositoryModel>) {
        val entities = repositoryModels.map {
            it.toRepositoryEntity()
        }.toTypedArray()

        gitHubDatabase.repositories.saveRepositories(repositories = *entities)
    }

    override suspend fun savePulls(repository: String, pullModels: List<PullModel>) {
        val entities = pullModels.map {
            it.toPullEntity(repository)
        }.toTypedArray()

        gitHubDatabase.pulls.savePulls(pulls = *entities)
    }
}