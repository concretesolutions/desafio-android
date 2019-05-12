package com.gdavidpb.github.data.source

import com.gdavidpb.github.domain.model.Pull
import com.gdavidpb.github.domain.model.Repository
import com.gdavidpb.github.domain.model.SearchResult
import com.gdavidpb.github.domain.repository.VCSRepository

open class GitHubDataRepository(
    private val factory: GitHubDataStoreFactory
) : VCSRepository {
    override suspend fun getRepositories(page: Int): SearchResult<Repository> {
        val cache = factory.retrieveCacheDataStore()
        val remote = factory.retrieveRemoteDataStore()

        return remote.getRepositories(page).also {
            cache.saveRepositories(repositories = it.items)
        }
    }

    override suspend fun getPulls(repository: String): List<Pull> {
        val cache = factory.retrieveCacheDataStore()

        val cachedPulls = cache.getPulls(repository)

        return if (cachedPulls.isNotEmpty()) {
            cachedPulls
        } else {
            val remote = factory.retrieveRemoteDataStore()

            return remote.getPulls(repository).also {
                cache.savePulls(repository = repository, pulls = it)
            }
        }
    }
}