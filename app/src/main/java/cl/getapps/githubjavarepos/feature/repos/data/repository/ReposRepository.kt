package cl.getapps.githubjavarepos.feature.repos.data.repository

import cl.getapps.githubjavarepos.feature.repos.data.ReposResponse
import cl.getapps.githubjavarepos.feature.repos.data.remote.ReposParams
import cl.getapps.githubjavarepos.feature.repos.data.source.DataSourceFactory
import cl.getapps.githubjavarepos.feature.repos.data.source.RemoteDataSource
import cl.getapps.githubjavarepos.feature.repos.domain.Repos
import cl.getapps.githubjavarepos.feature.repos.domain.repository.ReposRepository
import io.reactivex.Completable
import io.reactivex.Flowable


class ReposRepository(private val dataStoreFactory: DataSourceFactory): ReposRepository {
    override fun fetchRepos(params: ReposParams): Flowable<ReposResponse> {
        return dataStoreFactory.retrieveCacheDataSource().isCached()
            .flatMapPublisher { dataStoreFactory.retrieveDataSource<RemoteDataSource>(it).fetchRepos(params) }
            .flatMap { save(it).toSingle { it }.toFlowable() }
    }

    fun save(items: ReposResponse): Completable {
        return dataStoreFactory.retrieveCacheDataSource().save(items)
    }

    fun clear(): Completable {
        return dataStoreFactory.retrieveCacheDataSource().clear()
    }

}
