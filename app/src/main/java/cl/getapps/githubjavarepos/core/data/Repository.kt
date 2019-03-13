package cl.getapps.githubjavarepos.core.data

import io.reactivex.Completable


interface Repository<T> {

    fun save(items: T): Completable

    fun clear(): Completable
}
