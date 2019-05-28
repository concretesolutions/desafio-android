package cl.jesualex.desafio_android.base.remote

import io.reactivex.observers.DisposableObserver

/**
 * Created by jesualex on 21-03-19.
 */
abstract class UseCaseObserver<T> : DisposableObserver<T>() {
    open override fun onNext(value: T) {}

    override fun onError(e: Throwable) { e.printStackTrace() }

    override fun onComplete() {}
}
