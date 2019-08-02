package br.com.concrete.githubconcretechallenge.features.repositories.datasource

import br.com.concrete.githubconcretechallenge.cache.CacheProviders
import br.com.concrete.githubconcretechallenge.features.repositories.model.RepositoryModel
import br.com.concrete.githubconcretechallenge.features.repositories.service.RepositoriesListRetrofit
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.rx_cache2.DynamicKey
import io.rx_cache2.EvictProvider

class RepositoriesListRemoteDataSource(
    private val repositoriesListRetrofit: RepositoriesListRetrofit,
    private val cacheProviders: CacheProviders?
) {

    fun getRepositoriesList(page: Int, invalidateCacheForMethod: Boolean = false): Single<List<RepositoryModel>> {
        val request = repositoriesListRetrofit.getRepositoriesList(page = page)
            .map { response -> response.items }
            .observeOn(AndroidSchedulers.mainThread())

        cacheProviders?.let { cacheProviders ->
            return cacheProviders.getRepositories(request, DynamicKey(page), EvictProvider(invalidateCacheForMethod))
        }

        return request
    }

}