package com.gdavidpb.github.data.source.local

import com.gdavidpb.github.data.repository.GitHubDataStore
import com.gdavidpb.github.domain.model.Pull
import com.gdavidpb.github.domain.model.Repository
import com.gdavidpb.github.utils.toPull
import com.gdavidpb.github.utils.toPullEntity
import com.gdavidpb.github.utils.toRepositoryEntity

open class GitHubCacheDataStore(
    private val gitHubDatabase: GitHubDatabase
) : GitHubDataStore {
    override suspend fun getRepositories(page: Int) =
        throw UnsupportedOperationException()

    override suspend fun getRepositoriesCount(): Long {
        return gitHubDatabase.repositories.getRepositoriesCount()
    }

    override suspend fun getPulls(repository: String): List<Pull> {
        return gitHubDatabase.pulls
            .getPulls(repository)
            .map { it.toPull() }
    }

    override suspend fun saveRepositories(repositories: List<Repository>) {
        val entities = repositories.map {
            it.toRepositoryEntity()
        }.toTypedArray()

        gitHubDatabase.repositories.saveRepositories(repositories = *entities)
    }

    override suspend fun savePulls(repository: String, pulls: List<Pull>) {
        val entities = pulls.map {
            it.toPullEntity(repository)
        }.toTypedArray()

        gitHubDatabase.pulls.savePulls(pulls = *entities)
    }
}