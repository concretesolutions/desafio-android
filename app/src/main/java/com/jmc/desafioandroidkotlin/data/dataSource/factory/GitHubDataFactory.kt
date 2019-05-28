package com.jmc.desafioandroidkotlin.data.dataSource.factory


import com.jmc.desafioandroidkotlin.domain.model.PullModel
import com.jmc.desafioandroidkotlin.domain.model.RepositoryModel
import com.jmc.desafioandroidkotlin.domain.model.SearchResultModel
import com.jmc.desafioandroidkotlin.domain.repository.GithubRepository

open class GitHubDataFactory(
    private val factory: GitHubDataStoreFactory
) : GithubRepository {


    override suspend fun getRepositories(page: Int): SearchResultModel<RepositoryModel> {
        val cache = factory.retrieveCacheDataStore()
        val remote = factory.retrieveRemoteDataStore()

        return remote.getRepositories(page).also {
            cache.saveRepositories(repositoryModels = it.items)
        }
    }

    override suspend fun getPulls(repository: String): List<PullModel> {
        val cache = factory.retrieveCacheDataStore()

        val cachedPulls = cache.getPulls(repository)

        return if (cachedPulls.isNotEmpty()) {
            cachedPulls
        } else {
            val remote = factory.retrieveRemoteDataStore()

            return remote.getPulls(repository).also {
                cache.savePulls(repository = repository, pullModels = it)
            }
        }
    }
}