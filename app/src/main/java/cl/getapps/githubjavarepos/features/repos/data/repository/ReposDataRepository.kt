package cl.getapps.githubjavarepos.features.repos.data.repository

import cl.getapps.githubjavarepos.features.repos.data.entity.ReposResponse
import cl.getapps.githubjavarepos.features.repos.data.remote.ReposParams
import cl.getapps.githubjavarepos.features.repos.data.remote.ReposRemoteDataSource
import cl.getapps.githubjavarepos.features.repos.data.source.ReposDataSourceFactory
import cl.getapps.githubjavarepos.features.repos.domain.repository.ReposRepository
import io.reactivex.Completable
import io.reactivex.Single


class ReposDataRepository(private val reposDataStoreFactory: ReposDataSourceFactory) : ReposRepository {

    override fun fetchRepos(params: ReposParams): Single<ReposResponse> {
        return reposDataStoreFactory.retrieveCacheDataSource().isCached()
            .flatMap {
                reposDataStoreFactory.retrieveDataSource<ReposRemoteDataSource>(it).fetchRepos(params)
            }
            .flatMap {
                save(it).toSingle { it }
            }
    }

    fun save(items: ReposResponse): Completable {
        return reposDataStoreFactory.retrieveCacheDataSource().save(items)
    }

    fun clear(): Completable {
        return reposDataStoreFactory.retrieveCacheDataSource().clear()
    }

}
