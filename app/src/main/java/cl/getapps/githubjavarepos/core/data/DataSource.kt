package cl.getapps.githubjavarepos.core.data

import io.reactivex.Completable
import io.reactivex.Single

interface DataSource

interface RemoteSource : DataSource

interface CacheSource : DataSource {

    fun isCached(): Single<Boolean>

    fun setLastCacheTime(lastCache: Long)

    fun isExpired(): Boolean

    fun <T> save(items: T): Completable

    fun clear(): Completable
}
