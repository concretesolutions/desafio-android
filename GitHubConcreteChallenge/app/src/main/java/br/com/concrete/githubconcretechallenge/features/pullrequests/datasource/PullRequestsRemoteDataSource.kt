package br.com.concrete.githubconcretechallenge.features.pullrequests.datasource

import br.com.concrete.githubconcretechallenge.cache.CacheProviders
import br.com.concrete.githubconcretechallenge.features.pullrequests.model.PullRequestModel
import br.com.concrete.githubconcretechallenge.features.pullrequests.service.PullRequestsRetrofit
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.rx_cache2.DynamicKey
import io.rx_cache2.EvictProvider

class PullRequestsRemoteDataSource(
    private val pullRequestsRetrofit: PullRequestsRetrofit,
    private val cacheProviders: CacheProviders?
) : PullRequestsDataSource {

    override fun getPullRequests(
        creatorName: String,
        repositoryName: String,
        invalidateCacheForMethod: Boolean
    ): Single<List<PullRequestModel>> {
        val request = pullRequestsRetrofit.getPullRequests(creatorName, repositoryName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        cacheProviders?.let { cacheProviders ->
            return cacheProviders.getPullRequests(
                request,
                DynamicKey("$creatorName$repositoryName"),
                EvictProvider(invalidateCacheForMethod)
            )
        }

        return request
    }

}