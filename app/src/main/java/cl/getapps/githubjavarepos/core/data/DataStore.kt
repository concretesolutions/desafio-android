package cl.getapps.githubjavarepos.core.data

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single


interface DataStore<T> {

    fun get(): Flowable<T>

    fun save(items: T): Completable

    fun clear(): Completable

    fun isCached(): Single<Boolean>

    fun isExpired(): Boolean

    fun setLastCacheTime(lastCache: Long)
}