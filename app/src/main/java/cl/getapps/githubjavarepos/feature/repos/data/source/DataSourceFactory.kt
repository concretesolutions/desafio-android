package cl.getapps.githubjavarepos.feature.repos.data.source

import cl.getapps.githubjavarepos.core.data.CacheSource
import cl.getapps.githubjavarepos.core.data.RemoteSource

class DataSourceFactory(
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
