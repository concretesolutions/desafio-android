package br.com.concrete.githubconcretechallenge.cache

import br.com.concrete.githubconcretechallenge.features.pullrequests.model.PullRequestModel
import br.com.concrete.githubconcretechallenge.features.repositories.model.RepositoryModel
import io.reactivex.Single
import io.rx_cache2.DynamicKey
import io.rx_cache2.EvictProvider

interface CacheProviders {

    fun getRepositories(repositoriesList: Single<List<RepositoryModel>>,
                        cacheKey: DynamicKey,
                        evictProvider: EvictProvider? = null): Single<List<RepositoryModel>>

    fun getPullRequests(
        pullRequestsList: Single<List<PullRequestModel>>,
        cacheKey: DynamicKey,
        evictProvider: EvictProvider? = null
    ): Single<List<PullRequestModel>>

}