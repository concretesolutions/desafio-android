package cl.getapps.githubjavarepos.core.data

import io.reactivex.Completable
import io.reactivex.Flowable


interface Repository<T, P> {

    open fun get(params: P): Flowable<T>

    open fun save(items: T): Completable

    open fun clear(): Completable
}

class None