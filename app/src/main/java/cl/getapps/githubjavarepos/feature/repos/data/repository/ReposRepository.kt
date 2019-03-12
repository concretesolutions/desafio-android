package cl.getapps.githubjavarepos.feature.repos.data.repository

import cl.getapps.githubjavarepos.core.data.DataStoreFactory
import cl.getapps.githubjavarepos.feature.repos.domain.Repos
import io.reactivex.Completable
import io.reactivex.Flowable


class ReposRepository(private val dataStoreFactory: DataStoreFactory<Repos>) {

    fun get(): Flowable<Repos> {
        return dataStoreFactory.retrieveCacheDataStore().isCached()
            .flatMapPublisher { dataStoreFactory.retrieveDataStore(it).get() }
            .flatMap { save(it).toSingle { it }.toFlowable() }
    }

    fun save(items: Repos): Completable {
        return dataStoreFactory.retrieveCacheDataStore().save(items)
    }

    fun clear(): Completable {
        return dataStoreFactory.retrieveCacheDataStore().clear()
    }

}