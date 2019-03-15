package cl.getapps.githubjavarepos.features.repos.data.source

import cl.getapps.githubjavarepos.core.data.CacheSource
import cl.getapps.githubjavarepos.core.data.RemoteSource

class ReposDataSourceFactory(
    val remoteDataSource: RemoteSource,
    val cacheDataSource: CacheSource
) {

    inline fun <reified A> retrieveDataSource(isCached: Boolean): A {
        if (isCached && !cacheDataSource.isExpired()) {
            return retrieveCacheDataSource() as A
        }
        return retrieveRemoteDataSource() as A
    }

    fun retrieveCacheDataSource(): CacheSource {
        return cacheDataSource
    }

    fun retrieveRemoteDataSource(): RemoteSource {
        return remoteDataSource
    }
}
